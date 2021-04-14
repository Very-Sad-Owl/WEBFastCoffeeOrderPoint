package by.epam.training.jwd.godot.dao.impl;

import by.epam.training.jwd.godot.bean.delivery_point.Address;
import by.epam.training.jwd.godot.bean.coffee.IngredientType;
import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.bean.delivery_point.Spot;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.dao.SpotsDao;
import by.epam.training.jwd.godot.dao.connection.ConnectionPool;
import by.epam.training.jwd.godot.dao.connection.ConnectionProvider;
import by.epam.training.jwd.godot.dao.connection.ecxeption.ConnectionPoolException;
import by.epam.training.jwd.godot.dao.constant.*;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.training.jwd.godot.dao.constant.AddressTable.ID;
import static by.epam.training.jwd.godot.dao.constant.CoffespotTable.*;
import static by.epam.training.jwd.godot.dao.constant.SQLQuery.*;

public class SpotsDaoImpl implements SpotsDao {

    private static final Logger LOGGER = Logger.getLogger(SpotsDaoImpl.class);

    @Override
    public List<Spot> getAll() throws DAOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        Connection con = null;

        List<Spot> all = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.prepareStatement(GET_ALL_COFFEE_SPOTS);
            rs = st.executeQuery();
            while(rs.next()) {
                long uid = rs.getLong(ID);
                double balance = rs.getDouble(BALANCE);
                double rating = rs.getDouble(RATING);
                long addressId = rs.getLong(ADDRESS);

                String region = rs.getString(CoveredRegionsTable.REGION);
                String region_ru = rs.getString(CoveredRegionsTable.REGION_RU);
                String city = rs.getString(CoveredCitiesTable.CITY);
                String city_ru = rs.getString(CoveredCitiesTable.CITY_RU);
                String street = rs.getString(AddressTable.STREET);
                String street_ru = rs.getString(AddressTable.STREET_RU);
                String house = rs.getString(AddressTable.HOUSE);
                Address address = new Address(region, region_ru, city, city_ru, street, street_ru, house);

                Spot spot = new Spot(uid, rating, balance, address);

                all.add(spot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st, rs);
            }
        }
        return all;
    }

    @Override
    public void deleteSpot(long uid) throws DAOException {
        PreparedStatement st = null;
        ConnectionPool pool = null;
        Connection con = null;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.prepareStatement(DELETE_SPOT);
            st.setLong(1, uid);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st);
            }
        }
    }

    @Override
    public void updateSpot(Address address, long uid) throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(UPDATE_REGION);
            ps.setString(1, address.getRegion());
            ps.setString(2, address.getRegionRu());
            ps.setLong(3, uid);
            ps.executeUpdate();

            ps = con.prepareStatement(UPDATE_CITY);
            ps.setString(1, address.getCity());
            ps.setString(2, address.getCityRu());
            ps.setLong(3,uid);
            ps.executeUpdate();

            ps = con.prepareStatement(UPDATE_ADDR_LINE);
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getStreetRu());
            ps.setString(3, address.getHouse());
            ps.setLong(4,uid);
            ps.executeUpdate();

            con.commit();
        } catch (SQLException | ConnectionPoolException e) {
            try {
                if (con != null) {
                    con.rollback();
                    LOGGER.info("pos insertion rollback\n");
                }
            } catch (SQLException e1) {
                throw new DAOException(e);
            }
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, ps);
            }
        }
    }

    @Override
    public void addSpot(Address address) throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(GET_REGION);
            ps.setString(1, address.getRegion());
            ResultSet rs = ps.executeQuery();
            int found = 0;
            if (rs.next()){
                found = rs.getInt("count");
            }
            if (found == 0){
                ps = con.prepareStatement(INSERT_REGION);
                ps.setString(1, address.getRegion());
                ps.setString(2, address.getRegionRu());
                ps.executeUpdate();
            }

            ps = con.prepareStatement(GET_CITY);
            ps.setString(1, address.getCity());
            rs = ps.executeQuery();
            found = 0;
            if (rs.next()){
                found = rs.getInt("count");
            }
            if (found == 0){
                ps = con.prepareStatement(INSERT_CITY);
                ps.setString(1, address.getCity());
                ps.setString(2, address.getCityRu());
                ps.setString(3, address.getRegion());
                ps.executeUpdate();
            }
            ps = con.prepareStatement(INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getRegion());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getStreet());
            ps.setString(4, address.getStreetRu());
            ps.setString(5, address.getHouse());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                long id = rs.getLong(1);
                ps = con.prepareStatement(INSERT_SPOT);
                ps.setLong(1, id);
                ps.executeUpdate();
            }
            con.commit();
        } catch (SQLException | ConnectionPoolException e) {
            try {
                if (con != null) {
                    con.rollback();
                    LOGGER.info("pos insertion rollback\n");
                }
            } catch (SQLException e1) {
                throw new DAOException(e);
            }
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, ps);
            }
        }
    }

    @Override
    public List<Ingredient> getSpotIngredients(long uid) throws DAOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        Connection con = null;

        List<Ingredient> all = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.prepareStatement(GET_SPOT_INGREDIENTS);
            st.setLong(1, uid);
            rs = st.executeQuery();
            while(rs.next()) {
                String title = rs.getString(CoffeeTable.INGREDIENT_TITLE);
                int amount = rs.getInt(RecepitsTable.AMOUNT);
                double price = rs.getDouble(CoffeeTable.INGREDIENT_PRICE);
                String imgSource = rs.getString(CoffeeTable.IMG);
                SeasonType seasonType = SeasonType.valueOf(rs.getString(CoffeeTable.SEASON_TITLE).toUpperCase());
                IngredientType ingredientType = IngredientType.valueOf(rs.getString(CoffeeTable.INGREDIENT_TYPE_TITLE).toUpperCase());

                Ingredient ingredient = new Ingredient(title, amount, price, ingredientType, imgSource, seasonType);

                all.add(ingredient);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st, rs);
            }
        }
        return all;
    }

    @Override
    public Ingredient getSpotIngredient(String title) throws DAOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        Connection con = null;

        Ingredient ingredient = null;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.prepareStatement(GET_SPOT_INGREDIENT);
            st.setString(1, title);
            rs = st.executeQuery();
            while(rs.next()) {
                int amount = rs.getInt(RecepitsTable.AMOUNT);
                double price = rs.getDouble(CoffeeTable.INGREDIENT_PRICE);
                String imgSource = rs.getString(CoffeeTable.IMG);
                SeasonType seasonType = SeasonType.valueOf(rs.getString(CoffeeTable.SEASON_TITLE).toUpperCase());
                IngredientType ingredientType = IngredientType.valueOf(rs.getString(CoffeeTable.INGREDIENT_TYPE_TITLE).toUpperCase());

                ingredient = new Ingredient(title, amount, price, ingredientType, imgSource, seasonType);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st, rs);
            }
        }
        return ingredient;
    }

    @Override
    public void buyIngredient(long spotUid, String ingredient, int amount) throws DAOException {
        PreparedStatement st = null;
        ConnectionPool pool = null;
        Connection con = null;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.prepareStatement(INSERT_INGREDIENT_INTO_STOGARE);
            st.setLong(1, spotUid);
            st.setString(2, ingredient);
            st.setInt(3, amount);

            st.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st);
            }
        }
    }

    @Override
    public void updateAmount(long spotUid, String ingredient, int amount) throws DAOException {
        PreparedStatement st = null;
        ConnectionPool pool = null;
        Connection con = null;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.prepareStatement(UPDATE_AMOUNT);
            st.setLong(1, spotUid);
            st.setString(2, ingredient);
            st.setInt(3, amount);

            st.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st);
            }
        }
    }
}
