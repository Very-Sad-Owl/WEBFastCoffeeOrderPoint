package by.epam.training.jwd.godot.service.impl;

import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.bean.user.SignInInfo;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.DaoProvider;
import by.epam.training.jwd.godot.dao.UserDao;
import by.epam.training.jwd.godot.service.exception.*;
import by.epam.training.jwd.godot.service.UserService;
import by.epam.training.jwd.godot.service.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

	public User authorization(SignInInfo info) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
        UserDao userDAO = provider.getUserDao();
        
		User user;
		try {
			user = userDAO.authorization(info);
			if (user == null){
				throw new NoSuchUserException("There is no such user");
			}
		}catch(DAOException e) {
			throw new ServiceException("Signing in error.", e);
		}
		return user;
	}

	@Override
	public boolean registration(RegistrationInfo regInfo) throws ServiceException {

		UserValidator.validateUserInfo(regInfo);

		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		try {
			if (userDAO.retrieveUser(regInfo.getLogin()) != null){
				throw new ReservedLoginException("This login already exists");
			}
			userDAO.registration(regInfo);
		} catch (DAOException e) {
			throw new ServiceException("Registration failed. Please, try again later.");
		}

		return true;
	}

	@Override
	public List<User> getAllUsers() throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		List<User> users = new ArrayList<>();

		try {
			users = userDAO.getAllUsers();
		} catch (DAOException e) {
			throw new ServiceException("Cannot retrieve users data.");
		}
		return users;
	}

	@Override
	public boolean deleteUser(String login) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		boolean res;

		try {
			res = userDAO.deleteUser(login);
		} catch (DAOException e) {
			throw new DeleteException("Cannot delete users data.");
		}
		return res;
	}

	@Override
	public boolean banUser(String login, boolean banned) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		boolean res;

		try {
			res = userDAO.banUser(login, banned);
		} catch (DAOException e) {
			throw new UpdateException("Cannot ban user.");
		}
		return res;
	}

	@Override
	public boolean changeAvatar(String login, String imgPath) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		boolean res;

		try {
			res = userDAO.changeAvatar(login, imgPath);
		} catch (DAOException e) {
			throw new UpdateException("Cannot update avatar.");
		}
		return res;
	}

	@Override
	public boolean changeUserContacts(String login, String email, String password) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		boolean res;

		try {
			res = userDAO.changeUserContacts(login, email, password);
		} catch (DAOException e) {
			throw new UpdateException("Cannot update user contacts.");
		}
		return res;
	}

	@Override
	public User retrieveUser(String login) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		User user;
		try {
			user = userDAO.retrieveUser(login);
		}catch(DAOException e) {
			throw new ServiceException("User retrieving in error.", e);
		}
		return user;
	}
}
