package by.epam.training.jwd.godot.service;

import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.bean.user.SignInInfo;
import by.epam.training.jwd.godot.service.exception.ServiceException;

import java.util.List;

public interface UserService {
	User authorization(SignInInfo logInfo) throws ServiceException;
	String registration(RegistrationInfo regInfo) throws ServiceException;
	List<User> getAllUsers() throws ServiceException;
	boolean deleteUser(String login) throws ServiceException;
	boolean banUser(String login, boolean banned) throws ServiceException;
	boolean changeAvatar(String login, String imgPath) throws ServiceException;
	void updateUserPassword(String email, String password, String pawConfirmation) throws ServiceException;
	void requestPasswordUpdateConfirmation(String email, String password, String passwordCheck) throws ServiceException;
	void resetUserPassword(String email) throws ServiceException;
	User retrieveUser(String login) throws ServiceException;
	User retrieveUserByEmail(String email) throws ServiceException;
	User retrieveUser(String login, String hash) throws ServiceException;
	void checkEmailBanned(RegistrationInfo info) throws ServiceException;
	void activateAccount(User user) throws ServiceException;
}
