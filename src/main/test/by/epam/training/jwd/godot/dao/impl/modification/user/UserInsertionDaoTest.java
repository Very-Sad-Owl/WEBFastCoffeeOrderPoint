package by.epam.training.jwd.godot.dao.impl.modification.user;

import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.UserDaoImpl;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class UserInsertionDaoTest {
    private UserDaoImpl dao = new UserDaoImpl();

//    @Test
//    public void registrationTest_correctData_true() throws DAOException {
//        RegistrationInfo info = new RegistrationInfo("test_owl", "Osamu_529123", "olga.mailychko@gmail.com");
//        boolean res = dao.registration(info);
//
//        assertTrue(res);
//    }
//
//    @Test(expected = DAOException.class)
//    public void registrationTest_nullData_false() throws DAOException {
//        RegistrationInfo info = new RegistrationInfo(null, "Osamu_529123", "olga.mailychko@gmail.com");
//        dao.registration(info);
//    }
//
//    @Test(expected = DAOException.class)
//    public void registrationTest_duplicatedData_false() throws DAOException {
//        RegistrationInfo info = new RegistrationInfo("test_owl", "Osamu_529123", "olga.mailychko@gmail.com");
//        dao.registration(info);
//    }
}