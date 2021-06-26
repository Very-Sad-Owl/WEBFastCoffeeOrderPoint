package by.epam.training.jwd.godot.dao.impl.modification.user;

import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRemovingDaoTest {
    private UserDaoImpl dao = new UserDaoImpl();

    @Test
    public void deleteUserTest_existingUser_true() throws DAOException {
        boolean res = dao.deleteUser("test_owl");
        assertTrue(res);
    }

    @Test
    public void deleteUserTest_nonexistingUser_true() throws DAOException {
        boolean res = dao.deleteUser("someuser");
        assertFalse(res);
    }

    @Test(expected = DAOException.class)
    public void deleteUserTest_daoException() throws DAOException {
        UserDaoImpl dictMock = mock(UserDaoImpl.class);
        when(dictMock.deleteUser(anyString()))
                .thenThrow(DAOException.class);

        dictMock.deleteUser("someuser");
    }
}