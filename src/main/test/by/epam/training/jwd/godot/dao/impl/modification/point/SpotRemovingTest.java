package by.epam.training.jwd.godot.dao.impl.modification.point;

import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.SpotsDaoImpl;
import org.junit.Test;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SpotRemovingTest {

    private SpotsDaoImpl dao = new SpotsDaoImpl();

    @Test
    public void deleteSpot_existingSpot() throws DAOException {
        long uid = 8;
        boolean res = dao.deleteSpot(uid);
        assertTrue(res);
    }

    @Test(expected = DAOException.class)
    public void deleteSpot_daoException() throws DAOException {
        long uid = 12;
        SpotsDaoImpl dictMock = mock(SpotsDaoImpl.class);
        doThrow(DAOException.class)
                .when(dictMock)
                .deleteSpot(anyLong());

        dictMock.deleteSpot(uid);
    }
}
