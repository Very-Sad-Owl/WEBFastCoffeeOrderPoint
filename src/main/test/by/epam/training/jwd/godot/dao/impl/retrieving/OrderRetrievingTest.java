package by.epam.training.jwd.godot.dao.impl.retrieving;

import by.epam.training.jwd.godot.bean.coffee.*;
import by.epam.training.jwd.godot.dao.CoffeeDao;
import by.epam.training.jwd.godot.dao.OrderDao;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.CoffeeDaoImpl;
import by.epam.training.jwd.godot.dao.impl.OrderDaoImpl;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

public class OrderRetrievingTest {
    private OrderDao dao = new OrderDaoImpl();

    @Test
    public void getAllBeverages_retrievedData() throws DAOException {

    }

    @Test(expected = DAOException.class)
    public void getAllBeverages_daoException() throws DAOException {
        CoffeeDaoImpl dictMock = mock(CoffeeDaoImpl.class);
        Mockito.when(dictMock.getAllBeverages())
                .thenThrow(DAOException.class);

        dictMock.getAllBeverages();
    }


}
