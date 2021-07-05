package by.epam.training.jwd.godot.dao.impl.retrieving;

import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.bean.delivery_point.Address;
import by.epam.training.jwd.godot.bean.delivery_point.Spot;
import by.epam.training.jwd.godot.dao.SpotsDao;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.impl.SpotsDaoImpl;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SpotRetrievingTest {
    private SpotsDao dao = new SpotsDaoImpl();

    @Test
    public void getAllSpots_retrievedData() throws DAOException {
        Spot first = new Spot(1, 0, 100,
                new Address("Minskaya", "Минск", "Minsk", "Минск",
                        "Surganova", "Сурганова", "37/2"));
        Spot second = new Spot(2, 0, 500,
                new Address("Grodnenskaya", "Гродненская", "Grodno", "Гродно",
                        "Lenina", "Ленина", "132"));
        Spot third = new Spot(1, 0, 100,
                new Address("Li Yue", "Ли Юэ", "Li Yue", "Ли Юэ",
                        "Main", "Главная", "35f"));
        List<Spot> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);
        expected.add(third);

        List<Spot> actual = dao.getAll();

        assertEquals(expected, actual);
    }

    @Test(expected = DAOException.class)
    public void getAllSpots_daoException() throws DAOException {
        SpotsDaoImpl dictMock = mock(SpotsDaoImpl.class);
        Mockito.when(dictMock.getAll())
                .thenThrow(DAOException.class);

        dictMock.getAll();
    }



}
