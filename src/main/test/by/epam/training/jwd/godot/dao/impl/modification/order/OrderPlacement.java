package by.epam.training.jwd.godot.dao.impl.modification.order;

import by.epam.training.jwd.godot.bean.order_element.PaymentMethod;
import by.epam.training.jwd.godot.dao.OrderDao;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class OrderPlacement {
    private final OrderDao dao = new OrderDaoImpl();

    @Test
    public void placeOrderTest_correctData() throws DAOException {
        Map<Long, Integer> positions = new HashMap<>();
        positions.put(1L, 1);
        positions.put(2L, 3);
        dao.placeOrder(positions, 37, 1, PaymentMethod.CARD, 20);
    }

    @Test(expected = DAOException.class)
    public void placeOrderTest_incorrectData() throws DAOException {
        Map<Long, Integer> positions = null;
        dao.placeOrder(positions, 2, 1, PaymentMethod.CARD, 20);
    }
}
