package by.epam.training.jwd.godot.dao.constant;

public interface UserTable {

    String USERS_TABLE = "users";
    String ID_COL = "id";
    String LOGIN_COL = "login";
    String PASSWORD_COL = "password";
    String EMAIL_COL = "email";
    String BALANCE_COL = "balance";
    String ROLE_COL = "role_id";
    String USER_ROLE = "role_title";
    String USER_IMG = "img_path";
    int GUEST_ROLE_ID = 1;
    int USER_ROLE_ID = 2;
    int ADMIN_ROLE_ID = 3;

}
