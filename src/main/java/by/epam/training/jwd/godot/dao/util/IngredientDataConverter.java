package by.epam.training.jwd.godot.dao.util;

import by.epam.training.jwd.godot.bean.IngredientType;
import by.epam.training.jwd.godot.bean.SeasonType;
import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import javafx.scene.control.Tab;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class IngredientDataConverter {

    private static final Logger LOGGER = Logger.getLogger(IngredientDataConverter.class);

    private enum TableRows{
        TITLE("title"), PRICE("price"), IMG("img_source"), AMOUNT("amount"),
        TYPE("type"), SEASON("season");

        private final String rowTitle;

        private TableRows(String rowTitle){
            this.rowTitle = rowTitle;
        }


        @Override
        public String toString() {
            return rowTitle;
        }
    }

    private enum JoinedTableRows{
        TITLE("ingredient_title"), PRICE("ingredient_price"), IMG("ingredient_img_source"),
        AMOUNT("ingredient_amount"), TYPE("ingredient_type"), SEASON("ingredient_season");

        private final String rowTitle;

        private JoinedTableRows(String rowTitle){
            this.rowTitle = rowTitle;
        }


        @Override
        public String toString() {
            return rowTitle;
        }
    }

    public IngredientDataConverter(){}

    public Ingredient retrieveFromResultSet(ResultSet rs) throws SQLException {
        String title = rs.getString(TableRows.TITLE.toString());
        double price = rs.getDouble(TableRows.PRICE.toString());
        String img = rs.getString(TableRows.IMG.toString());
        String ingredientType = rs.getString(TableRows.TYPE.toString());
        String seasonType = rs.getString(TableRows.SEASON.toString());

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientType(IngredientType.valueOf(ingredientType.toUpperCase()));
        ingredient.setImgPath(img);
        ingredient.setCoast(price);
        ingredient.setSeasonType(SeasonType.valueOf(seasonType.toUpperCase()));
        ingredient.setTitle(title);

        return ingredient;
    }

    public Ingredient retrieveFromJoinQueryResultSet(ResultSet rs) throws SQLException {
        String title = rs.getString(JoinedTableRows.TITLE.toString());
        double price = rs.getDouble(JoinedTableRows.PRICE.toString());
        String img = rs.getString(JoinedTableRows.IMG.toString());
        int quantity = rs.getInt(JoinedTableRows.AMOUNT.toString());
        String ingredientType = rs.getString(JoinedTableRows.TYPE.toString());
        String seasonType = rs.getString(JoinedTableRows.SEASON.toString());

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientType(IngredientType.valueOf(ingredientType));
        ingredient.setImgPath(img);
        ingredient.setCoast(price);
        ingredient.setQuantity(quantity);
        ingredient.setSeasonType(SeasonType.valueOf(seasonType));
        ingredient.setTitle(title);

        return ingredient;
    }
}
