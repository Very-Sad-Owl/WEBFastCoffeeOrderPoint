package by.epam.training.jwd.godot.dao.impl;

import by.epam.training.jwd.godot.bean.coffee.IngredientType;
import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.bean.coffee.CoffeeSize;
import by.epam.training.jwd.godot.bean.coffee.CoffeeType;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

public class CoffeeDaoImplTest {

    private CoffeeDaoImpl dao = new CoffeeDaoImpl();
    private Method calculateCoast;

    @Before
    public void setUp() throws Exception {

        calculateCoast = CoffeeDaoImpl.class
                .getDeclaredMethod("calculateCoast", String.class);
        calculateCoast.setAccessible(true);
    }


    @Test
    public void getAll_coffeeList() throws DAOException {
        List<Coffee> all = dao.getAllBeverages();

        for (Coffee el : all){
            System.out.println(el);
        }
    }

    @Test
    public void getDecors_decorsList() throws DAOException {
        List<Ingredient> all = dao.getDecorators(SeasonType.SUMMER);

        for (Ingredient el : all){
            System.out.println(el);
        }
    }

    @Test
    public void getCoast_existingRow() throws InvocationTargetException, IllegalAccessException {
        String arg = "latte";

        double expected = 0.56;

        double actual = (Double)calculateCoast.invoke(dao, arg);

        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void getSizes_existingType() throws DAOException {
        CoffeeType type = CoffeeType.ESPRESSO;

        List<CoffeeSize> sizes = dao.getCoffeeTypeSizes(type);

        for (CoffeeSize el : sizes){
            System.out.println(el);
        }
    }

    @Test
    public void updateIngredient() throws DAOException {
        Ingredient ingredient = new Ingredient("dd", 1, 1, IngredientType.valueOf("DECORATION"),
                "ddd", SeasonType.valueOf("SUMMER"));

        boolean res = dao.updateIngredient(ingredient, "test");

        assertTrue(res);
    }

}