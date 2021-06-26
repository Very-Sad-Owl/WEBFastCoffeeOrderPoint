package by.epam.training.jwd.godot.dao.impl.suite;

import by.epam.training.jwd.godot.dao.impl.modification.order.NewPositionnsertion;
import by.epam.training.jwd.godot.dao.impl.modification.order.OrderCreation;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//fourth
@RunWith(Suite.class)
@Suite.SuiteClasses({OrderCreation.class, NewPositionnsertion.class} )
public class OrderPreparation {
}
