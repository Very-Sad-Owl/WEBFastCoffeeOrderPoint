package by.epam.training.jwd.godot.dao.impl.suite;

import by.epam.training.jwd.godot.dao.impl.modification.point.SpotEditingTest;
import by.epam.training.jwd.godot.dao.impl.modification.point.SpotInsertionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//second
@RunWith(Suite.class)
@Suite.SuiteClasses({SpotInsertionTest.class, SpotEditingTest.class} )
public class SpotAction {
}
