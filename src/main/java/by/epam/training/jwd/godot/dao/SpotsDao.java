package by.epam.training.jwd.godot.dao;

import by.epam.training.jwd.godot.bean.delivery_point.Address;
import by.epam.training.jwd.godot.bean.delivery_point.Spot;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.dao.exception.DAOException;

import java.util.List;

public interface SpotsDao {
    List<Spot> getAll() throws DAOException;
    void deleteSpot(long uid) throws DAOException;
    void updateSpot(Address address, long uid) throws DAOException;
    void addSpot(Address address) throws DAOException;
    List<Ingredient> getSpotIngredients(long uid) throws DAOException;
    Ingredient getSpotIngredient(String title) throws DAOException;
    void buyIngredient(long spotUid, String ingredient, int amount) throws DAOException;
    void updateAmount(long spotUid, String ingredient, int amount) throws DAOException;
}
