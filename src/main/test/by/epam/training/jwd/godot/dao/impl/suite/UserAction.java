package by.epam.training.jwd.godot.dao.impl.suite;

import by.epam.training.jwd.godot.dao.impl.modification.user.UserInsertionDaoTest;
import by.epam.training.jwd.godot.dao.impl.modification.user.UserUpdateDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//first
@RunWith(Suite.class)
@Suite.SuiteClasses({UserInsertionDaoTest.class, UserUpdateDaoTest.class} )
public class UserAction {

}
