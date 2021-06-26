package by.epam.training.jwd.godot.dao.constant.table_column;

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
    String HASH_PASSWORD_COL = "hash_password";
    String IS_ACTIVATED_COL = "is_activated";
    int GUEST_ROLE_ID = 1;
    int USER_ROLE_ID = 5;
    int ADMIN_ROLE_ID = 3;

}
