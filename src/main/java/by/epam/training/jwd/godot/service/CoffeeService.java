package by.epam.training.jwd.godot.service;

import by.epam.training.jwd.godot.bean.coffee.*;
import by.epam.training.jwd.godot.service.exception.ServiceException;

import java.util.List;

public interface CoffeeService {
    List<Coffee> getAllCoffee() throws ServiceException;
    List<Coffee> getAllAvailableCoffee();
    List<Ingredient> getDecorators() throws ServiceException;
    List<CoffeeSize> getSizes(CoffeeType type) throws ServiceException;
    List<Ingredient> getAllIngredients() throws ServiceException;
    List<String> getIngredientColumns() throws ServiceException;
    boolean deleteIngredient(String title) throws ServiceException;
    boolean updateIngredient(Ingredient ingredient, String originalTitle) throws ServiceException;
    boolean addIngredient(Ingredient ingredient) throws ServiceException;

}
