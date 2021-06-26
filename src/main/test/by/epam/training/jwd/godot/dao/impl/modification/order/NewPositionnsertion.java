package by.epam.training.jwd.godot.dao.impl.modification.order;

import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.bean.coffee.CoffeeSize;
import by.epam.training.jwd.godot.bean.coffee.CoffeeType;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.dao.OrderDao;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NewPositionnsertion {
    private final OrderDao dao = new OrderDaoImpl();

    @Test
    public void insertPos_correctData() throws DAOException {
        String login = "aether";
        List<Ingredient> ingredients = new ArrayList<>();
        List<Ingredient> decorations = new ArrayList<>();
        Coffee coffee = new Coffee(CoffeeType.LATTE, "description", 1.5, "d:/folder",
                new CoffeeSize(250, 1), ingredients, decorations);
        dao.addOrderPosition(coffee, login);
    }


    @Test(expected = DAOException.class) //non-existing size
    public void insertPos_nonExistingData() throws DAOException {
        String login = "aether";
        List<Ingredient> ingredients = new ArrayList<>();
        List<Ingredient> decorations = new ArrayList<>();
        Coffee coffee = new Coffee(CoffeeType.LATTE, "description", 1.5, "d:/folder",
                new CoffeeSize(100, 1), ingredients, decorations);
        dao.addOrderPosition(coffee, login);
    }
}
