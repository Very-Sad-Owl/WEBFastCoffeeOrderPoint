package by.epam.training.jwd.godot.dao.util;

import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.bean.user.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.training.jwd.godot.dao.constant.table_column.UserTable.*;
import static by.epam.training.jwd.godot.dao.constant.table_column.UserTable.USER_IMG;

public class UserDataConverter {

    public User resultSetToUser(ResultSet rs) throws SQLException {

            String foundLogin = rs.getString(LOGIN_COL);
            String foundPassword = rs.getString(PASSWORD_COL);
            String foundEmail = rs.getString(EMAIL_COL);
            UserRole foundRole = UserRole.valueOf(rs.getString(USER_ROLE).toUpperCase());
            String avatar = rs.getString(USER_IMG);

            return new User(foundLogin, foundPassword, foundEmail, foundRole, avatar);


    }
}
