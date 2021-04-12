package by.epam.training.jwd.godot.dao.impl;

import by.epam.training.jwd.godot.bean.Spot;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SpotsDaoImplTest {
    SpotsDaoImpl dao = new SpotsDaoImpl();

    @Test
    public void getAll() throws DAOException {
        List<Spot> spots = dao.getAll();
        for (Spot el : spots){
            System.out.println(el);
        }
    }
}