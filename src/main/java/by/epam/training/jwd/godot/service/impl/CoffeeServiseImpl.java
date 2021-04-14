package by.epam.training.jwd.godot.service.impl;

import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.bean.coffee.*;
import by.epam.training.jwd.godot.dao.CoffeeDao;
import by.epam.training.jwd.godot.dao.DaoProvider;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import by.epam.training.jwd.godot.service.util.SeasonManager;

import java.util.ArrayList;
import java.util.List;

public class CoffeeServiseImpl implements CoffeeService {
    @Override
    public List<Coffee> getAllCoffee() throws ServiceException {
        List<Coffee> coffeeList = new ArrayList<>();

        DaoProvider provider = DaoProvider.getInstance();
        CoffeeDao dao = provider.getCoffeeDao();

        try {
            coffeeList.addAll(dao.getAllBeverages());
        } catch (DAOException e) {
            throw new ServiceException("Cannot retrieve coffee data");
        }
        return coffeeList;
    }

    @Override
    public List<Coffee> getAllAvailableCoffee() {
        return null;
    }

    @Override
    public List<Ingredient> getDecorators() throws ServiceException {
        List<Ingredient> decorationList = new ArrayList<>();

        DaoProvider provider = DaoProvider.getInstance();
        CoffeeDao dao = provider.getCoffeeDao();

        try {
            decorationList.addAll(dao.getDecorators(SeasonType.of(SeasonManager.getCurrentMonth())));
        } catch (DAOException e) {
            throw new ServiceException("Cannot retrieve ingredient data");
        }
        return decorationList;
    }

    @Override
    public List<CoffeeSize> getSizes(CoffeeType type) throws ServiceException {
        List<CoffeeSize> sizesList = new ArrayList<>();

        DaoProvider provider = DaoProvider.getInstance();
        CoffeeDao dao = provider.getCoffeeDao();

        try {
            sizesList.addAll(dao.getCoffeeTypeSizes(type));
        } catch (DAOException e) {
            throw new ServiceException("Cannot retrieve sizes data");
        }
        return sizesList;
    }

    @Override
    public List<Ingredient> getAllIngredients() throws ServiceException {
        List<Ingredient> ingredientList = new ArrayList<>();

        DaoProvider provider = DaoProvider.getInstance();
        CoffeeDao dao = provider.getCoffeeDao();

        try {
            ingredientList.addAll(dao.getAllIngredients());
        } catch (DAOException e) {
            throw new ServiceException("Cannot retrieve ingredients data");
        }
        return ingredientList;
    }

    @Override
    public List<String> getIngredientColumns() throws ServiceException {
        List<String> ingredientCols = new ArrayList<>();

        DaoProvider provider = DaoProvider.getInstance();
        CoffeeDao dao = provider.getCoffeeDao();

        try {
            ingredientCols.addAll(dao.getIngredientColumns());
        } catch (DAOException e) {
            throw new ServiceException("Cannot retrieve ingredients data");
        }
        return ingredientCols;
    }

    @Override
    public boolean deleteIngredient(String title) throws ServiceException {
        boolean res = false;

        DaoProvider provider = DaoProvider.getInstance();
        CoffeeDao dao = provider.getCoffeeDao();

        try {
            res = dao.deleteIngredient(title);
        } catch (DAOException e) {
            throw new ServiceException("Cannot retrieve ingredients data");
        }
        return res;
    }

    @Override
    public boolean updateIngredient(Ingredient ingredient, String originalTitle) throws ServiceException {
        boolean res = false;

        DaoProvider provider = DaoProvider.getInstance();
        CoffeeDao dao = provider.getCoffeeDao();

        try {
            res = dao.updateIngredient(ingredient, originalTitle);
        } catch (DAOException e) {
            throw new ServiceException("Cannot update ingredients data");
        }
        return res;
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) throws ServiceException {
        boolean res = false;

        DaoProvider provider = DaoProvider.getInstance();
        CoffeeDao dao = provider.getCoffeeDao();

        try {
            res = dao.addIngredient(ingredient);
        } catch (DAOException e) {
            throw new ServiceException("Cannot insert ingredients data");
        }
        return res;
    }
}
