package by.epam.training.jwd.godot.dao.impl.point;

import by.epam.training.jwd.godot.bean.delivery_point.Address;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.SpotsDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpotInsertionTest {

    private SpotsDaoImpl dao = new SpotsDaoImpl();

    @Test
    public void addSpot_correctNewData() throws DAOException {
        Address address = new Address("Li Yue", "Ли Юэ", "Li Yue", "Ли Юэ", "Main", "Главная", "35f");
        long uid = dao.addSpot(address);
        assertNotEquals(-1, uid);
    }

    @Test
    public void addSpot_toExistingRegion() throws DAOException {
        Address address = new Address("Minskaya", "Минская", "Mir", "Мир", "Main", "Главная", "123а");
        long uid = dao.addSpot(address);
        assertNotEquals(-1, uid);
    }

    @Test(expected = DAOException.class)
    public void addSpot_nullData_daoException() throws DAOException {
        Address address = new Address(null, "Минская", "Mir", "Мир", "Main", "Главная", "123а");
        dao.addSpot(address);
    }


}
