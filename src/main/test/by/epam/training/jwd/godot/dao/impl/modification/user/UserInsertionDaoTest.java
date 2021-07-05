package by.epam.training.jwd.godot.dao.impl.modification.user;

import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserInsertionDaoTest {
    private UserDaoImpl dao = new UserDaoImpl();

    @Test
    public void registrationTest_correctData_true() throws DAOException {
        RegistrationInfo info = new RegistrationInfo("test_owl", "92cc1ad1226d422167aef29601bd1885", "olga.mailychko@gmail.com");
        String expected = "92cc1ad1226d422167aef29601bd1885";

        String actual = dao.registration(info);

        assertEquals(expected, actual);
    }

    @Test(expected = DAOException.class)
    public void registrationTest_nullData_false() throws DAOException {
        RegistrationInfo info = new RegistrationInfo(null, null, null);
        dao.registration(info);
    }

    @Test(expected = DAOException.class)
    public void registrationTest_duplicatedData_false() throws DAOException {
        RegistrationInfo info = new RegistrationInfo("test_owl", "92cc1ad1226d422167aef29601bd1885", "olga.mailychko@gmail.com");
        dao.registration(info);
    }
}