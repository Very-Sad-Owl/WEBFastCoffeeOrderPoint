package by.epam.training.jwd.godot.dao.impl.modification.coffee;

import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.bean.coffee.IngredientType;
import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.CoffeeDaoImpl;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CoffeeEditingTest {

    private CoffeeDaoImpl dao = new CoffeeDaoImpl();

    @Test
    public void updateIngredient_correctData() throws DAOException {
        String titleToUpdate = "water";
        Ingredient ingredient = new Ingredient("water", 0, 0.4, IngredientType.DECORATION, "d:/water", SeasonType.SUMMER);
        boolean res = dao.updateIngredient(ingredient, titleToUpdate);
        assertTrue(res);
    }

    @Test(expected = DAOException.class)
    public void updateSpot_incorrectData() throws DAOException {
        String titleToUpdate = "water";
        Ingredient ingredient = null;
        boolean res = dao.updateIngredient(ingredient, titleToUpdate);

    }
}
