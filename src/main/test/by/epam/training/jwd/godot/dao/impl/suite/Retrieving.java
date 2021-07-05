package by.epam.training.jwd.godot.dao.impl.suite;

import by.epam.training.jwd.godot.dao.impl.retrieving.CoffeeRetrievingTest;
import by.epam.training.jwd.godot.dao.impl.retrieving.SpotRetrievingTest;
import by.epam.training.jwd.godot.dao.impl.retrieving.UserRetrieving;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CoffeeRetrievingTest.class, SpotRetrievingTest.class, UserRetrieving.class} )
//prelast
public class Retrieving {
}
