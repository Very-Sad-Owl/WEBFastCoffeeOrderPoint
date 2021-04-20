package by.epam.training.jwd.godot.controller.command.resource;

public interface RequestParam {

    String UID = "uid";
    String ID = "id";
    String LOGIN = "login";
    String PASSWORD = "password";
    String EMAIL = "email";
    String ACTION = "action";
    String DELETE_ACTION = "delete";
    String UPDATE_ACTION = "update";
    String ADD_ACTION = "add";
    String BAN_ACTION = "ban";
    String UNBAN_ACTION = "unban";
    String CHOSEN_LANGUAGE = "language";
    String CHANGE_IMAGE = "upload_img";
    String PREVIOUS_URL = "previousUrl";

    //ingredients
    String INGREDIENT = "ingredient";
    String INGREDIENTS = "ingredient_list";
    String INGREDIENT_COLUMNS = "ingredient_columns";
    String INGREDIENT_TITLE = "title";
    String INGREDIENT_PREVIOUS_TITLE = "orig_title";
    String INGREDIENT_TYPE = "ingr_type";
    String INGREDIENT_SEASON = "season_type";
    String INGREDIENT_PRICE = "price";
    String INGREDIENT_QUANTITY = "quantity";
    String INGREDIENT_AMOUNT = "amount";
    String INGREDIENT_IMG = "img_path";
    String DECORATION = "decor";
    String DECORATION_AMOUNT = "decor_amount";

    String USER_LOGIN = "login";
    String USER_EMAIL = "email";
    String USER_PASSWORD = "password";

    //order
    String ORDER_UID = "order";
    String ORDER_LIST = "orders";
    String SELECTED_POSITIONS = "selected";
    String SELECTED_POSITIONS_AMOUNTS = "amount";
    String AVAILABLE_SPOTS = "spots";
    String AVAILABLE_PAYMENT_METHODS = "payment_methods";
    String ESTIMATED_TIME = "estimated_time";
    String BEVERAGE_SIZE = "size";

    //user
    String USERS_LIST = "users";
    String USER = "user";

    //point
    String REGION = "region";
    String CITY  = "city";
    String STREET = "street";
    String HOUSE = "house";
    String REGION_RU = "region_ru";
    String CITY_RU = "city_ru";
    String STREET_RU = "street_ru";
    String SPOT_UID = "spot_uid";

}
