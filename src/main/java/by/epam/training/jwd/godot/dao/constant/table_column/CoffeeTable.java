package by.epam.training.jwd.godot.dao.constant.table_column;

public interface CoffeeTable {

    String COFFEE_TYPES_TABLE = "coffee_types";
    String COFFE_TYPE_ID = "id";
    String TYPE = "title";
    String IMG = "img_source";
    String COFFEE_TYPE_COAST = "coast";

    String INGREDIENTS_TABLE = "ingredients";
    String INGREDIENTS_ID = "id";
    String INGREDIENT_TITLE = "title";
    String INGREDIENT_PRICE = "price";
    String INGREDIENT_QUANTITY = "amount";
    String INGREDIENT_TYPE_ID = "type_id";
    String INGREDIENT_SEASON_ID = "season_id";
    String INGREDIENT_IMG = "img_source";

    String SEASONS_TABLE = "seasons";
    String SEASON_TITLE = "season";
    String SEASON_ID = "id";

    String SIZES_TABLE = "sizes";
    String SIZE_ID = "id";
    String SIZE_VOL = "size";
    String SIZE_INCREMENT = "increment";
    String SIZE_COFFE_TYPE_ID = "coffee_type_id";

    String INGREDIENT_TYPE_TITLE = "type";
    String INGREDIENT_TYPE_TABLE = "ingredient_type";

}
