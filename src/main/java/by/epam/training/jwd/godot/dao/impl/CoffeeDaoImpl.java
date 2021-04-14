package by.epam.training.jwd.godot.dao.impl;

import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.bean.coffee.*;
import by.epam.training.jwd.godot.dao.CoffeeDao;
import by.epam.training.jwd.godot.dao.connection.ConnectionPool;
import by.epam.training.jwd.godot.dao.connection.ConnectionProvider;
import by.epam.training.jwd.godot.dao.connection.ecxeption.ConnectionPoolException;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.util.IngredientDataConverter;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.training.jwd.godot.dao.constant.CoffeeTable.*;
import static by.epam.training.jwd.godot.dao.constant.SQLQuery.*;

public class CoffeeDaoImpl implements CoffeeDao {

    private static final Logger LOGGER = Logger.getLogger(CoffeeDaoImpl.class);

    @Override
    public List<Coffee> getAllBeverages() throws DAOException {
        Statement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        Connection con = null;

        List<Coffee> all = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(String.format(GET_ALL_QUERY,
                    COFFEE_TYPES_TABLE));

            while(rs.next()) {
                String type = rs.getString(TYPE).toUpperCase();
                String imgPath = rs.getString(IMG);
                double rawPrice = rs.getDouble("coast");
                Coffee coffee = new Coffee(CoffeeType.valueOf(type), rawPrice, CoffeeType.valueOf(type).toString(), imgPath);

                List<Ingredient> compos = getBeverageIngredients(type);
                coffee.setIngredients(compos);

                all.add(coffee);
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

    public List<Ingredient> getBeverageIngredients(String bevTitle) throws DAOException {
        Statement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        Connection con = null;

        List<Ingredient> ingredients = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(String.format(GET_BEVERAGE_INGREDIENTS, bevTitle));

            while(rs.next()) {
               Ingredient ingredient = new IngredientDataConverter().retrieveFromResultSet(rs);
               ingredients.add(ingredient);

            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st, rs);
            }
        }
        return ingredients;
    }

    @Override
    public List<Coffee> getAvailableBeverages() throws DAOException {
       return null;
    }

    @Override
    public List<Ingredient> getDecorators(SeasonType season) throws DAOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        Connection con = null;

        List<Ingredient> ingredients = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.prepareStatement(GET_SEASONAL_INGREDIENTS);
            st.setString(1, season.toString());
            rs = st.executeQuery();

            while(rs.next()) {
                Ingredient ingredient = new IngredientDataConverter().retrieveFromResultSet(rs);
                ingredients.add(ingredient);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st, rs);
            }
        }
        return ingredients;
    }

    @Override
    public List<Ingredient> getAllIngredients() throws DAOException {
        Statement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        Connection con = null;

        List<Ingredient> ingredients = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(GET_INGREDIENTS);

            while(rs.next()) {
                Ingredient ingredient = new IngredientDataConverter().retrieveFromResultSet(rs);
                ingredients.add(ingredient);

            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st, rs);
            }
        }
        return ingredients;
    }

    @Override
    public List<String> getIngredientColumns() throws DAOException {
        Statement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        Connection con = null;

        List<String> columns = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(GET_INGREDIENTS);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount(); //number of column
            String label;

            for (int i = 1; i <= count; i++) {
                label = metaData.getColumnLabel(i);
                if (!label.equals(INGREDIENTS_ID)) {
                    columns.add(label);
                }
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st, rs);
            }
        }
        return columns;
    }

    @Override
    public List<CoffeeSize> getCoffeeTypeSizes(CoffeeType type) throws DAOException {
        Statement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        Connection con = null;

        List<CoffeeSize> sizes = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(String.format(GET_COFFEE_SIZES,
                    SIZE_VOL, SIZE_INCREMENT, SIZES_TABLE, SIZE_COFFE_TYPE_ID,
                    COFFE_TYPE_ID, COFFEE_TYPES_TABLE, TYPE, type));

            while(rs.next()) {
                int vol = rs.getInt(SIZE_VOL);
                double incr = rs.getDouble(SIZE_INCREMENT);
                CoffeeSize sizeEl = new CoffeeSize(vol, incr);
                sizes.add(sizeEl);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st, rs);
            }
        }
        return sizes;

    }


    @Override
    public boolean deleteIngredient(String title) throws DAOException {
        PreparedStatement st = null;
        ConnectionPool pool = null;
        Connection con = null;
        boolean res = false;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.prepareStatement(DELETE_INGREDIENT);
            st.setString(1, title);
            res = st.executeUpdate() != 0;

            LOGGER.info(st);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st);
            }
        }
        return res;
    }

    @Override
    public boolean updateIngredient(Ingredient ingredient, String originalTitle) throws DAOException {
        PreparedStatement st = null;
        ConnectionPool pool = null;
        Connection con = null;
        boolean res = false;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.prepareStatement(UPDATE_INGREDIENT);
            st.setString(1, ingredient.getTitle());
            st.setDouble(2, ingredient.getCoast());
            st.setString(3, ingredient.getIngredientType().toString());
            st.setString(4, ingredient.getImgPath());
            st.setString(5, ingredient.getSeasonType().toString());
            st.setString(6, originalTitle);

            res = st.executeUpdate() != 0;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st);
            }
        }
        return res;
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) throws DAOException {
        PreparedStatement st = null;
        ConnectionPool pool = null;
        Connection con = null;
        boolean res = false;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            st = con.prepareStatement(INSERT_INGREDIENT);
            st.setString(1, ingredient.getTitle());
            st.setDouble(2, ingredient.getCoast());
            st.setString(3, ingredient.getIngredientType().toString());
            st.setString(4, ingredient.getImgPath());
            st.setString(5, ingredient.getSeasonType().toString());

            res = st.executeUpdate() != 0;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, st);
            }
        }
        return res;
    }

    @Override
    public List<Coffee> getAllRecepits() throws DAOException {
//        PreparedStatement st = null;
//        ResultSet rs = null;
//        ConnectionPool pool = null;
//        Connection con = null;
//
//        List<Coffee> all = new ArrayList<>();
//
//        try {
//            pool = ConnectionProvider.getConnectionPool();
//            con = pool.takeConnection();
//            st = con.prepareStatement(GET_ALL_RECEPITS);
//            rs = st.executeQuery(String.format(GET_ALL_QUERY,
//                    COFFEE_TYPES_TABLE));
//
//            String previousType = null;
//            while(rs.next()) {
//                String type = rs.getString("coffee_title");
//                if (previousType == null || !previousType.equals(type)) {
//                    previousType = type;
//                    String imgPath = rs.getString("coffee_img");
//                    Coffee coffee = new Coffee(CoffeeType.valueOf(type), CoffeeType.valueOf(type).toString(), imgPath);
//                    coffee.setCoast(calculateCoast(coffee.getType().toString()));
//                }
//
//                List<Ingredient> compos = getBeverageIngredients(type);
//                coffee.setIngredients(compos);
//
//                all.add(coffee);
//            }
//        } catch (SQLException | ConnectionPoolException e) {
//            throw new DAOException(e);
//        } finally {
//            if (pool != null) {
//                pool.closeConnection(con, st, rs);
//            }
//        }
//        return all;
        return new ArrayList<>();
    }
}
