package by.epam.training.jwd.godot.dao.impl.retrieving.user;

import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.bean.user.SignInInfo;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.bean.user.UserRole;
import by.epam.training.jwd.godot.dao.UserDao;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRetrieving {

    private UserDao dao = new UserDaoImpl();

//    @Test
//    public void authorization_existingAdminUser() throws DAOException {
//        User expected = new User
//                ("admin", "admin", "admin@gmail.com", 0, UserRole.ADMIN,
//                        "resources/image/profile_img/default.png");
//        SignInInfo inInfo = new SignInInfo("admin", "admin");
//        User actual  = dao.authorization(inInfo);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void authorization_existingUser() throws DAOException {
//        User expected = new User
//                ("aether", "whereismysis", "travaler@gmail.com", 0, UserRole.USER,
//                        "resources/image/profile_img/default.png");
//        SignInInfo inInfo = new SignInInfo("aether", "whereismysis");
//        User actual  = dao.authorization(inInfo);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void checkBanned_bannedUser_true() throws DAOException {
//        String login = "aether";
//        String password = "whereismysis";
//        String email = "travaler@gmail.com";
//        RegistrationInfo info = new RegistrationInfo(login, password, email);
//
//        boolean actual = dao.checkBanned(info);
//
//        assertTrue(actual);
//    }
//
//    @Test
//    public void checkBanned_notBannedUser_false() throws DAOException {
//        String login = "admin";
//        String password = "admin";
//        String email = "admin@gmail.com";
//        RegistrationInfo info = new RegistrationInfo(login, password, email);
//
//        boolean actual = dao.checkBanned(info);
//
//        assertFalse(actual);
//    }
//
//    @Test
//    public void retrieveUserTest_existingUser() throws DAOException {
//        User expected = new User
//                ("aether", "whereismysis", "travaler@gmail.com", 0, UserRole.USER,
//                        "resources/image/profile_img/default.png");
//        String login = "aether";
//
//        User actual = dao.retrieveUser(login);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void retrieveUserTest_nonExistingUser() throws DAOException {
//        String login = "nosuchuser";
//
//        User actual = dao.retrieveUser(login);
//
//        assertNull(actual);
//    }
}
