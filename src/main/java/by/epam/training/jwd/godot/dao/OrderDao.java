package by.epam.training.jwd.godot.dao;

import by.epam.training.jwd.godot.bean.Order;
import by.epam.training.jwd.godot.bean.OrderStatus;
import by.epam.training.jwd.godot.bean.PaymentMethod;
import by.epam.training.jwd.godot.bean.User;
import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.dao.exception.DAOException;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    void addOrderPosition(Coffee position, User user) throws DAOException;
    Order getOrder(User user, OrderStatus status) throws DAOException;
    void placeOrder(Map<Long, Integer> positions, long orderUid, long spotUid, PaymentMethod paymentMethod,
    int estimatedTime) throws DAOException;
    OrderStatus getOrderStatus(long uid) throws DAOException;
    List<Order> getInProcessOrders(long spotUid) throws DAOException;
    void changeOrderStatus(long uid, OrderStatus status) throws DAOException;
}
