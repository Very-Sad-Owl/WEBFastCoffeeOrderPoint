package by.epam.training.jwd.godot.service;

import by.epam.training.jwd.godot.bean.delivery_point.Spot;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.service.exception.ServiceException;

import java.util.List;

public interface SpotsService {
    List<Spot> getAll() throws ServiceException;
    void deleteSpot(long uid) throws ServiceException;
    void updateSpot(String region, String regionRu, String city, String cityRu,
                    String street, String streetRu, String house, long uid) throws ServiceException;
    void addSpot(String region, String regionRu, String city, String cityRu,
                 String street, String streetRu, String house) throws ServiceException;
    List<Ingredient> getSpotIngredients(long uid) throws ServiceException;
    Ingredient getSpotIngredient(String title) throws ServiceException;
    void buyIngredient(long spotUid, String ingredient, int amount) throws ServiceException;
    void updateIngredientAmount(long spotUid, String ingredient, int amount) throws ServiceException;
}
