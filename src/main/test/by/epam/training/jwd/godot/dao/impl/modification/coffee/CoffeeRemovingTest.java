package by.epam.training.jwd.godot.dao.impl.modification.coffee;

import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.CoffeeDaoImpl;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CoffeeRemovingTest {

    private CoffeeDaoImpl dao = new CoffeeDaoImpl();

    @Test
    public void deleteIngredient() throws DAOException {
        String title = "test";
        boolean res = dao.deleteIngredient(title);
        assertTrue(res);
    }

    @Test(expected = DAOException.class)
    public void deleteIngredient_sqlException_true() throws DAOException {
        CoffeeDaoImpl dictMock = mock(CoffeeDaoImpl.class);
        when(dictMock.deleteIngredient(anyString()))
                .thenThrow(DAOException.class);

        dictMock.deleteIngredient(new String());
    }
}
