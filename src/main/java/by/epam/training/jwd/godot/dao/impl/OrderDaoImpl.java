package by.epam.training.jwd.godot.dao.impl;

import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.bean.coffee.CoffeeSize;
import by.epam.training.jwd.godot.bean.coffee.CoffeeType;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.bean.delivery_point.Spot;
import by.epam.training.jwd.godot.bean.order_element.Order;
import by.epam.training.jwd.godot.bean.order_element.OrderPotition;
import by.epam.training.jwd.godot.bean.order_element.OrderStatus;
import by.epam.training.jwd.godot.bean.order_element.PaymentMethod;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.dao.OrderDao;
import by.epam.training.jwd.godot.dao.connection.ConnectionPool;
import by.epam.training.jwd.godot.dao.connection.ConnectionProvider;
import by.epam.training.jwd.godot.dao.connection.ecxeption.ConnectionPoolException;
import by.epam.training.jwd.godot.dao.constant.table_column.OrderTable;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.util.IngredientDataConverter;
import by.epam.training.jwd.godot.dao.util.OrderDataConverter;
import by.epam.training.jwd.godot.dao.util.SpotDataConverter;
import by.epam.training.jwd.godot.dao.util.UserDataConverter;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.epam.training.jwd.godot.dao.constant.table_column.CoffeeTable.*;
import static by.epam.training.jwd.godot.dao.constant.table_column.OrderPositions.AMOUNT;
import static by.epam.training.jwd.godot.dao.constant.SQLQuery.*;
import static by.epam.training.jwd.godot.dao.constant.table_column.OrderTable.*;

public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    @Override
    public void addOrderPosition(Coffee position, String login) throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;
        ResultSet rs = null;

        long orderId = 0;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(CHECK_ORDER);
            ps.setString(1, login);
            ps.setString(2, OrderStatus.NEW.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                orderId = rs.getLong(ID);
            }
            if (orderId == 0){
                orderId = insertEmptyOrder(login);
            }

            ps = con.prepareStatement(ADD_POSITION, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, position.getType().toString().toLowerCase());
            ps.setInt(2, position.getSize().getVolume());
            ps.setString(3, position.getType().toString().toLowerCase());
            ps.setLong(4, orderId);

            int rowAffected = ps.executeUpdate();
            LOGGER.info(ps+"\n");

            if (rowAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    long rowId = rs.getInt(1);
                    StringBuilder wholeQuery = new StringBuilder();
                    wholeQuery.append(ADD_ORDER_DECORS);
                    int counter = 0;
                    for (Ingredient el : position.getDecorations()) {
                        wholeQuery.append(String.format(DECOR_VALUES, rowId, el.getTitle().toLowerCase(), el.getQuantity())).append(",");
                        counter++;
                    }
                    if (counter != 0) {
                        wholeQuery.deleteCharAt(wholeQuery.length() - 1);
                        ps = con.prepareStatement(wholeQuery.toString());
                        LOGGER.info(ps + "\n");
                        ps.executeUpdate();
                    }

                    ps = con.prepareStatement(SET_POSITION_COAST);
                    ps.setLong(1, rowId);
                    ps.setLong(2, rowId);
                    ps.executeUpdate();

                    ps = con.prepareStatement(UPDATE_ORDER_COAST);
                    ps.setLong(1, orderId);
                    ps.setLong(2, orderId);
                    ps.executeUpdate();

                    LOGGER.info("pos insertion commit\n");
                    con.commit();
                } else {
                    LOGGER.info("pos insertion rollback\n");
                    con.rollback();
                }
            } else {
                con.rollback();
                throw new DAOException();
            }
        } catch (SQLException | ConnectionPoolException e) {
            try {
                if (con != null) {
                    con.rollback();
                    LOGGER.info("pos insertion rollback");
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


    private List<OrderPotition> getUserCart(long uid) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet srs = null;
        ConnectionPool pool = null;
        Connection con = null;

        List<OrderPotition> all = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            ps = con.prepareStatement(GET_CURRENT_ORDER);
            ps.setLong(1, uid);
            rs = ps.executeQuery();

            while(rs.next()) {
                long id = rs.getLong(OrderTable.ID);
                String type = rs.getString(TYPE).toUpperCase();
                String imgPath = rs.getString(IMG);
                String size = rs.getString(SIZE_VOL).toUpperCase();
                double increment = rs.getDouble(SIZE_INCREMENT);
                double price = rs.getDouble(PRICE);
                int amount = rs.getInt(AMOUNT);

                Coffee coffee = new Coffee(CoffeeType.valueOf(type), imgPath, new CoffeeSize(Integer.parseInt(size),increment));

                List<Ingredient> ingredients = new ArrayList<>();
                ps = con.prepareStatement(GET_POSITION_INGREDIENTS);
                ps.setString(1, type);
                srs = ps.executeQuery();
                while (srs.next()){
                    Ingredient ingredient = new IngredientDataConverter().retrieveFromResultSet(srs);
                    ingredients.add(ingredient);
                }
                coffee.setIngredients(ingredients);

                ps = con.prepareStatement(GET_POSITION_DECORATIONS);
                ps.setLong(1, id);
                srs = ps.executeQuery();
                while (srs.next()){
                    Ingredient ingredient = new IngredientDataConverter().retrieveDecorationFromResultSet(srs);
                    coffee.addDecoration(ingredient);
                }
                all.add(new OrderPotition(id, coffee, amount, price));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            if (pool != null) {
                pool.closeConnection(con, ps, rs);
            }
        }
        return all;
    }

    private long insertEmptyOrder(String login) throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;
        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            ps = con.prepareStatement(INSERT_EMPTY_ORDER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, login);
            ps.setString(2, OrderStatus.NEW.toString());
            ps.setInt(3, 5);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new DAOException();
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException();
        } finally {
            if (pool != null) {
                pool.closeConnection(con, ps);
            }
        }
    }

    @Override
    public Order getOrder(User user, OrderStatus status) throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;
        ResultSet rs = null;
        Order found = new Order();
        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            ps = con.prepareStatement(GET_ORDER);
            ps.setString(1, status.toString());
            ps.setString(2, user.getLogin());
            rs = ps.executeQuery();
            if (rs.next()) {
                found.setUid(rs.getLong(OrderTable.ID));
                found.setUser(user);
                found.setStatus(status);
                found.setCoast(rs.getDouble(PRICE));
                found.setEstimatedTime(rs.getInt(ESTIMATED_TIME));
                if (found.getStatus() != OrderStatus.NEW) {
                    found.setDate(new Date(rs.getTimestamp(DATE).getTime()));
                }

                //for spot
                Spot spot = null;
                ps = con.prepareStatement(GET_SPOT);
                ps.setLong(1, rs.getLong(DELIVERY_POINT_ID));
                rs = ps.executeQuery();
                if(rs.next()) {
                    spot = new SpotDataConverter().resultSetToSpot(rs);
                }
                found.setAddress(spot);
                List<OrderPotition> positions = getUserCart(found.getUid());
                found.setPositions(positions);
            }
            return found;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException();
        } finally {
            if (pool != null) {
                pool.closeConnection(con, ps, rs);
            }
        }
    }

    @Override
    public void placeOrder(Map<Long, Integer> positions, long orderUid, long spotUid, PaymentMethod paymentMethod, int estimatedTime) throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;

        if (positions == null){
            throw new DAOException("null positions data");
        }

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            con.setAutoCommit(false);

            StringBuilder uidToDelete = new StringBuilder();

            for (Map.Entry<Long, Integer> entry : positions.entrySet()) {
                uidToDelete.append(entry.getKey()).append(",");
                ps = con.prepareStatement(SET_POSITION_AMOUNT);
                ps.setInt(1, entry.getValue());
                ps.setLong(2, entry.getKey());
                ps.executeUpdate();
            }
            uidToDelete.deleteCharAt(uidToDelete.length() - 1);

            ps = con.prepareStatement(String.format(DELETE_POSITIONS, uidToDelete));
            ps.setLong(1, orderUid);
            ps.executeUpdate();

            ps = con.prepareStatement(UPDATE_ORDER_TO_PLACED);
            ps.setInt(1, estimatedTime);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(3, spotUid);
            ps.setString(4, paymentMethod.toString());
            ps.setString(5, OrderStatus.PLACED.toString());
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
    public OrderStatus getOrderStatus(long uid) throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;
        ResultSet rs = null;
        OrderStatus status = null;

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();

            ps = con.prepareStatement(GET_ORDER_STATUS);
            ps.setLong(1, uid);
            rs = ps.executeQuery();
            if (rs.next()){
                status = OrderStatus.valueOf(rs.getString(STATUS).toUpperCase());
            }
            return status;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException();
        } finally {
            if (pool != null) {
                pool.closeConnection(con, ps, rs);
            }
        }

    }

    @Override
    public Order getOrder(long uid) throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;
        ResultSet rs = null;
        Order found = new Order();
        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            ps = con.prepareStatement(GET_ORDER_BY_UID);
            ps.setLong(1, uid);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new UserDataConverter().resultSetToUser(rs);
                found.setUid(uid);
                found.setUser(user);
                found.setStatus(OrderStatus.valueOf(rs.getString(STATUS).toUpperCase()));
                found.setCoast(rs.getDouble(PRICE));
                found.setEstimatedTime(rs.getInt(ESTIMATED_TIME));
                found.setDate(new Date(rs.getTimestamp(DATE).getTime()));

                //for spot
                Spot spot = null;
                ps = con.prepareStatement(GET_SPOT);
                ps.setLong(1, rs.getLong(DELIVERY_POINT_ID));
                rs = ps.executeQuery();
                if(rs.next()) {
                    spot = new SpotDataConverter().resultSetToSpot(rs);
                }

                found.setAddress(spot);

                List<OrderPotition> positions = getUserCart(found.getUid());

                found.setPositions(positions);
            }
            return found;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException();
        } finally {
            if (pool != null) {
                pool.closeConnection(con, ps, rs);
            }
        }
    }

    @Override
    public List<Order> getInProcessOrders(long spotUid) throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;
        ResultSet rs = null;
        List<Order> all = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();

            if (spotUid != -1) {
                ps = con.prepareStatement(GET_IN_PROCESS_ORDERS_BY_SPOT);
                ps.setLong(1, spotUid);
            } else {
                ps = con.prepareStatement(GET_IN_PROCESS_ORDERS);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Order found = new OrderDataConverter().retrieveFromResultSet(rs);

                //user
                User user = new UserDataConverter().resultSetToUser(rs);

                found.setUser(user);

                //for spot
                Spot spot = null;
                ps = con.prepareStatement(GET_SPOT);
                ps.setLong(1, rs.getLong(DELIVERY_POINT_ID));
                ResultSet srs = ps.executeQuery();
                if(srs.next()) {
                    spot = new SpotDataConverter().resultSetToSpot(srs);
                }

                found.setAddress(spot);

                List<OrderPotition> positions = getUserCart(found.getUid());

                found.setPositions(positions);
                all.add(found);
            }
            return all;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException();
        }finally {
            if (pool != null) {
                pool.closeConnection(con, ps);
            }
        }
    }

    @Override
    public List<Order> getOrders() throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;
        ResultSet rs = null;
        List<Order> all = new ArrayList<>();

        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();

            ps = con.prepareStatement(GET_ORDERS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order found = new OrderDataConverter().retrieveFromResultSet(rs);

                //user
                User user = new UserDataConverter().resultSetToUser(rs);

                found.setUser(user);

                //for spot
                Spot spot = null;
                ps = con.prepareStatement(GET_SPOT);
                ps.setLong(1, rs.getLong(DELIVERY_POINT_ID));
                ResultSet srs = ps.executeQuery();
                if (srs.next()) {
                    spot = new SpotDataConverter().resultSetToSpot(srs);
                }

                found.setAddress(spot);

                List<OrderPotition> positions = getUserCart(found.getUid());

                found.setPositions(positions);
                all.add(found);
            }
            return all;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException();
        }finally {
            if (pool != null) {
                pool.closeConnection(con, ps);
            }
        }
    }

    @Override
    public void changeOrderStatus(long uid, OrderStatus status) throws DAOException {
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        Connection con = null;
        try {
            pool = ConnectionProvider.getConnectionPool();
            con = pool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(SET_ORDER_STATUS);
            ps.setString(1, status.toString());
            ps.setLong(2, uid);
            ps.executeUpdate();

            if (status == OrderStatus.COMPLETED){
                ps = con.prepareStatement(SET_INCOME);
                ps.setLong(1, uid);
                ps.executeUpdate();
            } else if (status == OrderStatus.FORGOTTEN){
                ps = con.prepareStatement(SET_CONSUMPTION);
                ps.setLong(1, uid);
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
}
