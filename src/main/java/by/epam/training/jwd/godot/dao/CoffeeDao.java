package by.epam.training.jwd.godot.dao;

import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.bean.coffee.*;
import by.epam.training.jwd.godot.dao.exception.DAOException;

import java.util.List;

public interface CoffeeDao {
    List<Coffee> getAllBeverages() throws DAOException;

    List<Ingredient> getBeverageIngredients(String bevTitle) throws DAOException;

    List<Coffee> getAvailableBeverages() throws DAOException;
    List<Ingredient> getDecorators(SeasonType seasonType) throws DAOException;
    List<CoffeeSize> getCoffeeTypeSizes(CoffeeType type) throws DAOException;
    List<Ingredient> getAllIngredients() throws DAOException;
    List<String> getIngredientColumns() throws DAOException;
    boolean deleteIngredient(String title) throws DAOException;
    boolean updateIngredient(Ingredient ingredient, String originalTitle) throws DAOException;
    boolean addIngredient(Ingredient ingredient) throws DAOException;
}
