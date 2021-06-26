package by.epam.training.jwd.godot.dao.impl.modification.point;

import by.epam.training.jwd.godot.bean.delivery_point.Address;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.SpotsDaoImpl;
import org.junit.Test;

public class SpotEditingTest {

    private SpotsDaoImpl dao = new SpotsDaoImpl();

    @Test
    public void updateSpot_correctData() throws DAOException {
        Address address = new Address("Mogilevskaya", "Могилёвская", "Mogilev", "Могилёв",
                "Sovetskaya", "Советская", "1");
        dao.updateSpot(address, 3);
    }

    @Test(expected = DAOException.class)
    public void updateSpot_incorrectData() throws DAOException {
        Address address = new Address(null, "Минская", "Mir", "Мир", "Main", "Главная", "123а");
        dao.updateSpot(address, 3);
    }
}
