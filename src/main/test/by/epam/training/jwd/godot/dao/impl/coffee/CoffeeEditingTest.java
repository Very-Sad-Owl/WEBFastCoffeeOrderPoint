package by.epam.training.jwd.godot.dao.impl.coffee;

import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.bean.coffee.IngredientType;
import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.bean.delivery_point.Address;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.CoffeeDaoImpl;
import by.epam.training.jwd.godot.dao.impl.SpotsDaoImpl;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CoffeeEditingTest {

    private CoffeeDaoImpl dao = new CoffeeDaoImpl();

    @Test
    public void updateIngredient_correctData() throws DAOException {
        String titleToUpdate = "test";
        Ingredient ingredient = new Ingredient("newtest", 0, 0.2, IngredientType.DECORATION, "d:/newtest", SeasonType.SUMMER);
        boolean res = dao.updateIngredient(ingredient, titleToUpdate);
        assertTrue(res);
    }

    @Test(expected = DAOException.class)
    public void updateSpot_incorrectData() throws DAOException {
        String titleToUpdate = "test";
        Ingredient ingredient = null;
        boolean res = dao.updateIngredient(ingredient, titleToUpdate);

    }
}
