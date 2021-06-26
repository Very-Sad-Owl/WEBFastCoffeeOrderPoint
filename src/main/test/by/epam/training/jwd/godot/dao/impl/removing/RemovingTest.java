package by.epam.training.jwd.godot.dao.impl.removing;

import by.epam.training.jwd.godot.bean.delivery_point.Address;
import by.epam.training.jwd.godot.bean.delivery_point.Spot;
import by.epam.training.jwd.godot.dao.CoffeeDao;
import by.epam.training.jwd.godot.dao.OrderDao;
import by.epam.training.jwd.godot.dao.SpotsDao;
import by.epam.training.jwd.godot.dao.UserDao;
import by.epam.training.jwd.godot.dao.connection.ConnectionPool;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.CoffeeDaoImpl;
import by.epam.training.jwd.godot.dao.impl.OrderDaoImpl;
import by.epam.training.jwd.godot.dao.impl.SpotsDaoImpl;
import by.epam.training.jwd.godot.dao.impl.UserDaoImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

public class RemovingTest {
    private SpotsDao spotsDao = new SpotsDaoImpl();
    private CoffeeDao coffeeDao = new CoffeeDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private static long insertedSpotId  = -1;

    @BeforeClass
    public static void setUp(){
        PreparedStatement st = null;
        ResultSet rs = null;
        ConnectionPool pool = null;
        Connection con = null;
        String getInsertedSpotId = "select id from coffeespot where address_id = 3";
        try {
            st = con.prepareStatement(getInsertedSpotId);
            rs = st.executeQuery();
            if (rs.next()){
                insertedSpotId = rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteSpot_correctData() throws DAOException {
        boolean res = spotsDao.deleteSpot(insertedSpotId);
        assertTrue(res);
    }

    @Test(expected = DAOException.class)
    public void deleteSpot_incorrectData() throws DAOException {
        SpotsDaoImpl dictMock = mock(SpotsDaoImpl.class);
        Mockito.when(dictMock.deleteSpot(anyLong()))
                .thenThrow(DAOException.class);

        dictMock.deleteSpot(-1);
    }

    @Test
    public void deleteIngredient_correctData() throws DAOException {
        boolean res = coffeeDao.deleteIngredient("test");
        assertTrue(res);
    }

    @Test(expected = DAOException.class)
    public void deleteIngredient_incorrectData() throws DAOException {
        CoffeeDaoImpl dictMock = mock(CoffeeDaoImpl.class);
        Mockito.when(dictMock.deleteIngredient(anyString()))
                .thenThrow(DAOException.class);

        dictMock.deleteIngredient("test");
    }

}
