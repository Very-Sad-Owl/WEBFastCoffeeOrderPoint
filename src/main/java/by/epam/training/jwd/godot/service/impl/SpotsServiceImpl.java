package by.epam.training.jwd.godot.service.impl;

import by.epam.training.jwd.godot.bean.delivery_point.Address;
import by.epam.training.jwd.godot.bean.delivery_point.Spot;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.dao.DaoProvider;
import by.epam.training.jwd.godot.dao.SpotsDao;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.service.SpotsService;
import by.epam.training.jwd.godot.service.exception.InsertionException;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import by.epam.training.jwd.godot.service.exception.UpdateException;

import java.util.ArrayList;
import java.util.List;

public class SpotsServiceImpl implements SpotsService {
    @Override
    public List<Spot> getAll() throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        SpotsDao dao = provider.getSpotsDao();

        List<Spot> spots = new ArrayList<>();
        try {
            spots.addAll(dao.getAll());
        } catch (DAOException e) {
            throw new ServiceException("Cannot retrieve spots data");
        }
        return spots;
    }

    @Override
    public void deleteSpot(long uid) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        SpotsDao dao = provider.getSpotsDao();

        try {
            dao.deleteSpot(uid);
        } catch (DAOException e) {
            throw new ServiceException("Cannot delete spot data");
        }
    }

    @Override
    public void updateSpot(String region, String regionRu, String city, String cityRu,
                           String street, String streetRu, String house, long uid) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        SpotsDao dao = provider.getSpotsDao();

        try {
            Address updated = new Address(region, regionRu, city, cityRu, street, streetRu, house);
            dao.updateSpot(updated, uid);
        } catch (DAOException e) {
            throw new ServiceException("Cannot update spot data");
        }
    }

    @Override
    public void addSpot(String region, String regionRu, String city, String cityRu,
                        String street, String streetRu, String house) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        SpotsDao dao = provider.getSpotsDao();

        try {
            Address address = new Address(region, regionRu, city, cityRu, street, streetRu, house);
            dao.addSpot(address);
        } catch (DAOException e) {
            throw new ServiceException("cannot insert new spot");
        }
    }

    @Override
    public List<Ingredient> getSpotIngredients(long uid) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        SpotsDao dao = provider.getSpotsDao();
        List<Ingredient> ingredients;
        try {
            ingredients = dao.getSpotIngredients(uid);
        } catch (DAOException e) {
            throw new ServiceException("cannot insert new spot");
        }
        return ingredients;
    }

    @Override
    public Ingredient getSpotIngredient(String title) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        SpotsDao dao = provider.getSpotsDao();
        Ingredient ingredient;
        try {
            ingredient = dao.getSpotIngredient(title);
        } catch (DAOException e) {
            throw new ServiceException("cannot retrieve ingredient data");
        }
        return ingredient;
    }

    @Override
    public void buyIngredient(long spotUid, String ingredient, int amount) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        SpotsDao dao = provider.getSpotsDao();
        try {
            dao.buyIngredient(spotUid, ingredient,  amount);
        } catch (DAOException e) {
            throw new InsertionException("cannot buy new ingredient");
        }
    }

    @Override
    public void updateIngredientAmount(long spotUid, String ingredient, int amount) throws ServiceException {
        DaoProvider provider = DaoProvider.getInstance();
        SpotsDao dao = provider.getSpotsDao();
        try {
            dao.updateAmount(spotUid, ingredient,  amount);
        } catch (DAOException e) {
            throw new UpdateException("cannot buy more ingredient");
        }
    }
}
