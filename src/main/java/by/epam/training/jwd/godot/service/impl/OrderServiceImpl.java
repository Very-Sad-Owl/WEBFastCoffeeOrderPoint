package by.epam.training.jwd.godot.service.impl;

import by.epam.training.jwd.godot.bean.order_element.Order;
import by.epam.training.jwd.godot.bean.order_element.OrderStatus;
import by.epam.training.jwd.godot.bean.order_element.PaymentMethod;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.dao.DaoProvider;
import by.epam.training.jwd.godot.dao.OrderDao;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.service.OrderService;
import by.epam.training.jwd.godot.service.exception.InsertionException;
import by.epam.training.jwd.godot.service.exception.RetrievingException;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import by.epam.training.jwd.godot.service.exception.UpdateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrderPosition(Coffee position, User user) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        OrderDao dao = provider.getOrderDao();

        try {
            dao.addOrderPosition(position, user);
        } catch (DAOException e) {
            throw new InsertionException("Order position insertion has failed");
        }
    }

    @Override
    public Order getCartPositions(User user) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        OrderDao dao = provider.getOrderDao();

        try {
            return dao.getOrder(user, OrderStatus.NEW);
        } catch (DAOException e) {
            throw new RetrievingException("Cannot retrieve cart data");
        }
    }

    @Override
    public void placeOrder(long orderUid, long spotUid, PaymentMethod paymentMethod,
                           String[] selectedPos, String[] selectedAmounts, int estimatedTime) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        OrderDao dao = provider.getOrderDao();

        Map<Long, Integer> positions = new HashMap<>();
        int counter = 0;
        for (String el : selectedPos){
            while (selectedAmounts[counter].isEmpty()){
                counter++;
            }
            positions.put(Long.parseLong(el), Integer.parseInt(selectedAmounts[counter]));
            counter++;
        }

        try {
            dao.placeOrder(positions, orderUid, spotUid, paymentMethod, estimatedTime);
        } catch (DAOException e) {
            throw new InsertionException("cannot place order");
        }
    }

    @Override
    public OrderStatus getOrderStatus(long orderUid) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        OrderDao dao = provider.getOrderDao();

        try {
            return dao.getOrderStatus(orderUid);
        } catch (DAOException e) {
            throw new RetrievingException("cannot retrieve order data");
        }
    }

    @Override
    public List<Order> getInProcessOrders(long spotUid) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        OrderDao dao = provider.getOrderDao();

        try {
            return dao.getInProcessOrders(spotUid);
        } catch (DAOException e) {
            throw new RetrievingException("cannot retrieve in-process order data");
        }
    }

    @Override
    public void changeOrderStatus(long uid, OrderStatus status) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        OrderDao dao = provider.getOrderDao();

        try {
            dao.changeOrderStatus(uid, status);
        } catch (DAOException e) {
            throw new UpdateException("cannot change order status");
        }
    }

    @Override
    public Order getOrder(long uid) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        OrderDao dao = provider.getOrderDao();

        try {
            return dao.getOrder(uid);
        } catch (DAOException e) {
            throw new RetrievingException("cannot retrieve order data");
        }
    }
}
