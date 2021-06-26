package by.epam.training.jwd.godot.dao.impl.suite;

import by.epam.training.jwd.godot.dao.impl.modification.coffee.CoffeeEditingTest;
import by.epam.training.jwd.godot.dao.impl.modification.coffee.CoffeeInsertionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//third
@RunWith(Suite.class)
@Suite.SuiteClasses({CoffeeInsertionTest.class, CoffeeEditingTest.class} )
public class CoffeeAction {
}
