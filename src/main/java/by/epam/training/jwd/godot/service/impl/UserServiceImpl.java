package by.epam.training.jwd.godot.service.impl;

import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.bean.user.SignInInfo;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.DaoProvider;
import by.epam.training.jwd.godot.dao.UserDao;
import by.epam.training.jwd.godot.service.exception.*;
import by.epam.training.jwd.godot.service.UserService;
import by.epam.training.jwd.godot.service.util.PasswordHasher;
import by.epam.training.jwd.godot.service.util.verification_message_sender.EmailSender;
import by.epam.training.jwd.godot.service.validator.UserValidator;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

	private static final EmailSender emailSender = new EmailSender();
	private static final PasswordHasher hasher = new PasswordHasher();

	public User authorization(SignInInfo info) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
        UserDao userDAO = provider.getUserDao();
		User user;
		try {
			String hashedPassword = hasher.hashPassword(info.getPassword());
			info.setPassword(hashedPassword);
			user = userDAO.authorization(info);
			if (user == null){
				throw new NoSuchUserException("There is no such user");
			}
		}catch(DAOException e) {
			throw new ServiceException("Signing in error.", e);
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException("Unknown error", e);
		}
		return user;
	}

	@Override
	public String registration(RegistrationInfo regInfo) throws ServiceException {

		UserValidator.validateUserInfo(regInfo);

		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();
		String hash;

		try {
			checkEmailBanned(regInfo);
			if (userDAO.retrieveUser(regInfo.getLogin()) != null){
				throw new ReservedLoginException("This login already exists");
			}
			String hashedPassword = hasher.hashPassword(regInfo.getPassword());
			regInfo.setPassword(hashedPassword);
			hash = userDAO.registration(regInfo);
		} catch (DAOException e) {
			throw new ServiceException("Registration failed. Please, try again later.");
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException("Unknown error", e);
		}

		return hash;
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
	public void updateUserPassword(String email, String password, String confirmationPassword) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		try {
			userDAO.changeUserPassword(email, hasher.hashPassword(password));
		} catch (DAOException e) {
			throw new UpdateException("Cannot update user password.");
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException("Unknown error", e);
		}
	}

	@Override
	public void requestPasswordUpdateConfirmation(String email, String password, String passwordCheck) throws ServiceException {
		try {
			if (password.equals(passwordCheck)) {
				emailSender.sendPasswordChangeApprove(email, password, passwordCheck);
			} else {
				throw new NewPasswordMismatchException();
			}
		} catch (MessagingException e) {
			throw new EmailSendingException("Failing sending email message");
		}
	}

	@Override
	public void resetUserPassword(String email) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();
		try {
			if (userDAO.retrieveUserByEmail(email) != null) {
				String autogeneratedPsw = new PasswordHasher().generatePassword();
				emailSender.sendAutogeneratedPassword(email, autogeneratedPsw);
				userDAO.changeUserPassword(email, hasher.hashPassword(autogeneratedPsw));
			} else {
				throw new NoSuchUserException("There is no user with such email");
			}
		} catch (DAOException e) {
			throw new UpdateException("Cannot update user's password.");
		} catch (MessagingException e) {
			throw new NewPasswordMismatchException("Password mismatch");
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException("Unknown error");
		}
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

	@Override
	public User retrieveUserByEmail(String email) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		User user;
		try {
			user = userDAO.retrieveUserByEmail(email);
		}catch(DAOException e) {
			throw new ServiceException("User retrieving in error.", e);
		}
		return user;
	}

	@Override
	public void checkEmailBanned(RegistrationInfo info) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		try {
			boolean res = userDAO.checkBanned(info);
			if (res){
				throw new BannedEmailException("this email has been banned");
			}
		}catch(DAOException e) {
			throw new ServiceException("User retrieving in error.", e);
		}
	}

	@Override
	public User retrieveUser(String email, String hash) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		User user;
		try {
			user = userDAO.retrieveUser(email, hash);
		}catch(DAOException e) {
			throw new ServiceException("User retrieving in error.", e);
		}
		return user;
	}

	@Override
	public void activateAccount(User user) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDAO = provider.getUserDao();

		try {
			userDAO.activateAccount(user);
		}catch(DAOException e) {
			throw new ServiceException("Account verification in error.", e);
		}
	}
}
