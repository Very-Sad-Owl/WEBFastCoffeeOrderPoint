package by.epam.training.jwd.godot.dao.impl.modification.order;

import by.epam.training.jwd.godot.dao.OrderDao;
import by.epam.training.jwd.godot.dao.impl.OrderDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class OrderCreation {
    private final OrderDao dao = new OrderDaoImpl();
    Method insertEmptyOrder;

    @Before
    public void setUp() throws Exception {
        insertEmptyOrder =
                dao.getClass()
                        .getDeclaredMethod("insertEmptyOrder", String.class);
        insertEmptyOrder.setAccessible(true);
    }


    @Test
    public void insertEmptyOrderTest_correctData() throws InvocationTargetException, IllegalAccessException {
        String userLogin = "aether";
        insertEmptyOrder.invoke(dao, userLogin);
    }

    @Test(expected = InvocationTargetException.class)
    public void insertEmptyOrderTest_incorrectData() throws InvocationTargetException, IllegalAccessException {
        String userLogin = null;
        insertEmptyOrder.invoke(dao, userLogin);
    }

    @Test(expected = InvocationTargetException.class)
    public void insertEmptyOrderTest_nonExistinUser() throws InvocationTargetException, IllegalAccessException {
        String userLogin = "nosuchuser";
        insertEmptyOrder.invoke(dao, userLogin);
    }

    @Test(expected = InvocationTargetException.class)
    public void insertEmptyOrderTest_sqlException() throws InvocationTargetException, IllegalAccessException {
        String userLogin = "aether";
        when(insertEmptyOrder.invoke(dao, anyString()))
                .thenThrow(SQLException.class);

        insertEmptyOrder.invoke(dao, userLogin);
    }
}
