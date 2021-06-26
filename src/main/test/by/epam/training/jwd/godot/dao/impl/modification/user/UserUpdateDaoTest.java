package by.epam.training.jwd.godot.dao.impl.modification.user;

import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserUpdateDaoTest {
    private UserDaoImpl dao = new UserDaoImpl();


    @Test
    public void banUserTest_ban() throws DAOException {
        boolean res = dao.banUser("aether", true);

        assertTrue(res);
    }

    @Test
    public void banUserTest_unban() throws DAOException {
        boolean res = dao.banUser("aether", false);

        assertTrue(res);
    }

    @Test(expected = DAOException.class)
    public void banUserTest_daoException() throws DAOException {
        UserDaoImpl dictMock = mock(UserDaoImpl.class);
        when(dictMock.banUser(anyString(), anyBoolean()))
                .thenThrow(DAOException.class);

        dictMock.banUser("aether", true);
    }


}