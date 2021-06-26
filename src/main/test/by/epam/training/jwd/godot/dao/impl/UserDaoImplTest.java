package by.epam.training.jwd.godot.dao.impl;

import by.epam.training.jwd.godot.bean.user.SignInInfo;
import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    private UserDaoImpl dao = new UserDaoImpl();

    @Test
    public void existingUserLogin() throws DAOException {
        User user = dao.authorization(new SignInInfo("admin", "admin"));

        assertNotNull(user);
    }

//    @Test
//    public void insertUserTest() throws DAOException {
//        boolean res = dao.registration(new RegistrationInfo("oleg", "720290", "oleg.gmail.com"));
//
//        assertNotEquals(0, res);
//    }

    @Test
    public void banUserTest() throws DAOException {
        boolean res = dao.banUser("oleg", false);

        assertNotEquals(0, res);
    }

    @Test
    public void changeUserContactsTest() throws DAOException {
        boolean res = dao.changeUserContacts("owl", "newowl", "owl@gmail.com");

        assertNotEquals(0, res);
    }

}