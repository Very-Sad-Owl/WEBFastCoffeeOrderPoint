package by.epam.training.jwd.godot.dao.impl;

import by.epam.training.jwd.godot.bean.user.SignInInfo;
import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.bean.user.UserRole;
import by.epam.training.jwd.godot.dao.exception.DAOException;
import by.epam.training.jwd.godot.dao.UserDao;
import by.epam.training.jwd.godot.dao.connection.ConnectionPool;
import by.epam.training.jwd.godot.dao.connection.ConnectionProvider;
import by.epam.training.jwd.godot.dao.connection.ecxeption.ConnectionPoolException;
import by.epam.training.jwd.godot.dao.util.UserDataConverter;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.training.jwd.godot.dao.constant.table_column.UserTable.*;
import static by.epam.training.jwd.godot.dao.constant.SQLQuery.*;

public class UserDaoImpl implements UserDao {

	private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
	private static final String AUTH_USER = "SELECT * FROM users join roles on users.role_id = roles.id WHERE login = ? AND password = ?";
	private static final String GET_USER = "SELECT * FROM users join roles on users.role_id = roles.id WHERE login = ?";
	private static final String GET_USER_WITH_HASH = "SELECT * FROM users join roles on users.role_id = roles.id WHERE email = ?  and hash_password = ?";
	private static final String INSERT_USER = "INSERT INTO %s(%s,%s,%s,%s, %s) values(\"%s\", \"%s\", \"%s\", %d, \"%s\")";
	private static final String DELETE_USER = "delete from users where login = ?";
	private static final String BAN_USER = "UPDATE users SET role_id = ? WHERE login = ?";
	private static final String CHANGE_IMG = "UPDATE users SET img_path = ? WHERE login = ?";
	private static final String CHANGE_CONTACTS = "UPDATE users SET email = ?, password = ? WHERE login = ?";
	private static final String CHECK_EMAIL_BANNED = "select count(*) from users where email = ? and role_id = 4";
	private static final String  ACTIVATE_ACCOUNT = "UPDATE users SET role_id = 2 where login = ? and hash_password = ?";

	public User authorization(SignInInfo info) throws DAOException {

		PreparedStatement st = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		Connection con = null;

		User user = null;

		try {
			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			st = con.prepareStatement(AUTH_USER);
			st.setString(1, info.getLogin());
			st.setString(2, info.getPassword());
			rs = st.executeQuery();

			while(rs.next()) {
				user = new UserDataConverter().resultSetToUser(rs);
			}
		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, st, rs);
			}
		}
		return user;
	}


	public boolean registration(RegistrationInfo info) throws DAOException {

		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		int res;

		try {

			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			ps = con.prepareStatement(String.format(INSERT_USER,
					USERS_TABLE, LOGIN_COL, PASSWORD_COL, EMAIL_COL, ROLE_COL, HASH_PASSWORD_COL,
					info.getLogin(), info.getPassword(), info.getEmail(), USER_ROLE_ID, info.getHashedPassword()));

			res = ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, ps);
			}
		}

		return res != 0;
	}

	@Override
	public List<User> getAllUsers() throws DAOException {
		Statement st = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		Connection con = null;

		List<User> users = new ArrayList<>();

		try {
			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(GET_ALL_USERS_DATA);

			while(rs.next()) {
//				String foundLogin = rs.getString(LOGIN_COL);
//				String foundPassword = rs.getString(PASSWORD_COL);
//				String foundEmail = rs.getString(EMAIL_COL);
//				double foundBalance = rs.getDouble(BALANCE_COL);
//				UserRole foundRole = UserRole.valueOf(rs.getString(USER_ROLE).toUpperCase());
//				String avatar = rs.getString(USER_IMG);
//
//				User user = new User(foundLogin, foundPassword, foundEmail, foundBalance, foundRole, avatar);
				User user = new UserDataConverter().resultSetToUser(rs);
				users.add(user);
			}
		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, st, rs);
			}
		}
		return users;
	}

	@Override
	public boolean deleteUser(String login) throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		int res;

		try {
			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			ps = con.prepareStatement(DELETE_USER);
			ps.setString(1, login);

			res = ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, ps);
			}
		}

		return res != 0;
	}

	@Override
	public boolean banUser(String login, boolean banned) throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		int res;

		try {
			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			ps = con.prepareStatement(BAN_USER);
			if(banned) {
				ps.setInt(1, UserRole.BANNED.ordinal());
			} else {
				ps.setInt(1, UserRole.USER.ordinal());
			}
			ps.setString(2, login);

			res = ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, ps);
			}
		}

		return res != 0;
	}

	@Override
	public boolean changeAvatar(String login, String imgPath) throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		int res;

		try {
			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			ps = con.prepareStatement(CHANGE_IMG);
			ps.setString(1, imgPath);
			ps.setString(2, login);

			res = ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, ps);
			}
		}
		return res != 0;
	}

	@Override
	public boolean changeUserContacts(String login, String newEmail, String password) throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		int res;

		try {
			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			ps = con.prepareStatement(CHANGE_CONTACTS);
			ps.setString(1, newEmail);
			ps.setString(2, password);
			ps.setString(3, login);

			res = ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, ps);
			}
		}
		return res != 0;
	}

	@Override
	public User retrieveUser(String login) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		Connection con = null;

		User user = null;

		try {
			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			st = con.prepareStatement(GET_USER);
			st.setString(1, login);
			rs = st.executeQuery();

			while(rs.next()) {
				user = new UserDataConverter().resultSetToUser(rs);
			}
		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, st, rs);
			}
		}
		return user;
	}

	@Override
	public User retrieveUser(String email,  String hash) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		Connection con = null;

		User user = null;

		try {
			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			st = con.prepareStatement(GET_USER_WITH_HASH);
			st.setString(1, email);
			st.setString(2, hash);
			rs = st.executeQuery();

			while(rs.next()) {
				user = new UserDataConverter().resultSetToUser(rs);
			}
		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, st, rs);
			}
		}
		return user;
	}

	@Override
	public boolean checkBanned(RegistrationInfo info) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		ConnectionPool pool = null;
		Connection con = null;

		try {
			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			st = con.prepareStatement(CHECK_EMAIL_BANNED);
			st.setString(1, info.getEmail());
			rs = st.executeQuery();

			int count = 0;
			if(rs.next()) {
				count = rs.getInt(1);
			}
			return count >= 1;
		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, st, rs);
			}
		}
	}

	@Override
	public void activateAccount(User user) throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		int res;

		try {
			pool = ConnectionProvider.getConnectionPool();
			con = pool.takeConnection();
			ps = con.prepareStatement(ACTIVATE_ACCOUNT);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getHashPassword());
			res = ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			LOGGER.error(e);
			throw new DAOException(e);
		} finally {
			if (pool != null) {
				pool.closeConnection(con, ps);
			}
		}
	}
}
