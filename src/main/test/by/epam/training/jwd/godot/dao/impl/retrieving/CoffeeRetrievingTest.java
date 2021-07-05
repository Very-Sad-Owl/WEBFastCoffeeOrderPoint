package by.epam.training.jwd.godot.dao.impl.retrieving;

import by.epam.training.jwd.godot.bean.coffee.*;
import by.epam.training.jwd.godot.bean.delivery_point.Address;
import by.epam.training.jwd.godot.bean.delivery_point.Spot;
import by.epam.training.jwd.godot.dao.CoffeeDao;
import by.epam.training.jwd.godot.dao.SpotsDao;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.CoffeeDaoImpl;
import by.epam.training.jwd.godot.dao.impl.SpotsDaoImpl;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

public class CoffeeRetrievingTest {
    private CoffeeDao dao = new CoffeeDaoImpl();

    @Test
    public void getAllBeverages_retrievedData() throws DAOException {
        List<Coffee> expected = new ArrayList<>();
        Coffee first = new Coffee(CoffeeType.ESPRESSO, 0.3, CoffeeType.ESPRESSO.toString(), "d:/coffee");
        Coffee second = new Coffee(CoffeeType.LATTE, 0, CoffeeType.LATTE.toString(), "d:/latte");
        expected.add(first);
        expected.add(second);

        List<Coffee> actual = dao.getAllBeverages();

        assertEquals(expected, actual);
    }

    @Test(expected = DAOException.class)
    public void getAllBeverages_daoException() throws DAOException {
        CoffeeDaoImpl dictMock = mock(CoffeeDaoImpl.class);
        Mockito.when(dictMock.getAllBeverages())
                .thenThrow(DAOException.class);

        dictMock.getAllBeverages();
    }

    @Test
    public void getBeverageIngredients_retrievedData() throws DAOException {
        String title = "espresso";
        List<Ingredient> expected = new ArrayList<>();
        Ingredient first = new Ingredient("water", 3, 0.66, IngredientType.MAIN, "resources/image/ingredients/water.png",
                SeasonType.ANY);
        expected.add(first);

        List<Ingredient> actual = dao.getBeverageIngredients(title);

        assertEquals(expected, actual);
    }

    @Test(expected = DAOException.class)
    public void getBeverageIngredients_daoException() throws DAOException {
        CoffeeDaoImpl dictMock = mock(CoffeeDaoImpl.class);
        Mockito.when(dictMock.getBeverageIngredients(anyString()))
                .thenThrow(DAOException.class);

        dictMock.getBeverageIngredients("espresso");
    }

    @Test
    public void getCoffeeTypeSizes() throws DAOException {
        List<CoffeeSize> expected = new ArrayList<>();
        CoffeeSize first = new CoffeeSize(250, 1);
        CoffeeSize second = new CoffeeSize(350, 1.4);
        CoffeeSize third = new CoffeeSize(450, 1.8);
        expected.add(first);
        expected.add(second);
        expected.add(third);

        List<CoffeeSize> actual = dao.getCoffeeTypeSizes(CoffeeType.ESPRESSO);

        assertEquals(expected, actual);
    }

    @Test(expected = DAOException.class)
    public void getCoffeeTypeSizes_daoException() throws DAOException {
        CoffeeDaoImpl dictMock = mock(CoffeeDaoImpl.class);
        Mockito.when(dictMock.getCoffeeTypeSizes(anyObject()))
                .thenThrow(DAOException.class);

        dictMock.getCoffeeTypeSizes(CoffeeType.ESPRESSO);
    }
}
