package by.epam.training.jwd.godot.controller.command.resource;

public interface RequestParam {

    String LOGIN = "login";
    String PASSWORD = "password";
    String EMAIL = "email";
    String INGREDIENTS = "ingredient_list";
    String INGREDIENT_COLUMNS = "ingredient_columns";
    String ACTION = "action";
    String DELETE_ACTION = "delete";
    String UPDATE_ACTION = "update";
    String ADD_ACTION = "add";
    String BAN_ACTION = "ban";
    String UNBAN_ACTION = "unban";
    String CHOSEN_LANGUAGE = "language";
    String CHANGE_IMAGE = "upload_img";

    String INGREDIENT_TITLE = "title";
    String INGREDIENT_PREVIOUS_TITLE = "orig_title";
    String INGREDIENT_TYPE = "ingr_type";
    String INGREDIENT_SEASON = "season_type";
    String INGREDIENT_PRICE = "price";
    String INGREDIENT_QUANTITY = "quantity";
    String INGREDIENT_IMG = "img_path";

    String USER_LOGIN = "login";
    String USER_PREV_LOGIN = "prev_login";
    String USER_EMAIL = "email";
    String USER_PASSWORD = "password";
    String USER_BALANCE = "balance";

    //order
    String ORDER_UID = "order";
    String SELECTED_POSITIONS = "selected";
    String SELECTED_POSITIONS_AMOUNTS = "amount";
    String AVAILABLE_SPOTS = "spots";
    String AVAILABLE_PAYMENT_METHODS = "payment_methods";
    String ESTIMATED_TIME = "estimated_time";
}
