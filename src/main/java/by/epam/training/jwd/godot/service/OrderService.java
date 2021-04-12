package by.epam.training.jwd.godot.service;

import by.epam.training.jwd.godot.bean.Order;
import by.epam.training.jwd.godot.bean.OrderStatus;
import by.epam.training.jwd.godot.bean.PaymentMethod;
import by.epam.training.jwd.godot.bean.User;
import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    void addOrderPosition(Coffee position, User user) throws ServiceException;
    Order getCartPositions(User user) throws ServiceException;
    void placeOrder(long orderUid, long spotUid, PaymentMethod paymentMethod,
                    String[] selectedPos, String[] selectedAmounts, int estimatedTime) throws ServiceException;
    OrderStatus getOrderStatus(long orderUid) throws ServiceException;
    List<Order> getInProcessOrders(long spotUid) throws ServiceException;
    void changeOrderStatus(long uid, OrderStatus status) throws ServiceException;
}
