package by.epam.training.jwd.godot.dao;


import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.bean.user.SignInInfo;
import by.epam.training.jwd.godot.dao.exception.DAOException;

import java.util.List;

public interface UserDao {
	
	User authorization (SignInInfo info) throws DAOException;
	boolean	registration(RegistrationInfo info) throws DAOException;
	List<User> getAllUsers() throws DAOException;
	boolean deleteUser(String login) throws DAOException;
	boolean banUser(String login, boolean banned) throws DAOException;
	boolean changeAvatar(String login, String imgPath) throws DAOException;
	boolean changeUserContacts(String login, String newEmail, String password) throws DAOException;
	User retrieveUser(String login) throws DAOException;
	
}
