package by.epam.training.jwd.godot.dao.impl.coffee;

import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.CoffeeDaoImpl;
import by.epam.training.jwd.godot.dao.impl.SpotsDaoImpl;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
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
