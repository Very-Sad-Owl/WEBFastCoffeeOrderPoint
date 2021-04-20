package by.epam.training.jwd.godot.dao.impl.coffee;

import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.bean.coffee.IngredientType;
import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.CoffeeDaoImpl;
import by.epam.training.jwd.godot.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoffeeInsertionTest {

   public CoffeeDaoImpl dao = new CoffeeDaoImpl();

   @Test
    public void addIngredient_validData_true() throws DAOException {
       Ingredient ingredient = new Ingredient("test", 0, 0.1, IngredientType.DECORATION, "d:/test", SeasonType.SUMMER);
       boolean res = dao.addIngredient(ingredient);
       assertTrue(res);
   }

    @Test(expected = DAOException.class)
    public void addIngredient_invalidData_true() throws DAOException {
        Ingredient ingredient = null;
        dao.addIngredient(ingredient);
    }

    @Test(expected = DAOException.class)
    public void addIngredient_sqlException_true() throws DAOException {
        CoffeeDaoImpl dictMock = mock(CoffeeDaoImpl.class);
        when(dictMock.addIngredient(anyObject()))
                .thenThrow(DAOException.class);

        dictMock.addIngredient(new Ingredient());
    }
}
