package by.epam.training.jwd.godot.dao.util;

import by.epam.training.jwd.godot.bean.coffee.IngredientType;
import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.dao.constant.table_column.RecepitsTable;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;


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

        JoinedTableRows(String rowTitle){
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
        ingredient.setQuantity(0);

        return ingredient;
    }

    public Ingredient retrieveDecorationFromResultSet(ResultSet rs) throws SQLException {
        String title = rs.getString(TableRows.TITLE.toString());
        double price = rs.getDouble(TableRows.PRICE.toString());
        String img = rs.getString(TableRows.IMG.toString());
        String ingredientType = rs.getString(TableRows.TYPE.toString());
        String seasonType = rs.getString(TableRows.SEASON.toString());
        int amount = rs.getInt(TableRows.AMOUNT.toString());

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientType(IngredientType.valueOf(ingredientType.toUpperCase()));
        ingredient.setImgPath(img);
        ingredient.setCoast(price);
        ingredient.setSeasonType(SeasonType.valueOf(seasonType.toUpperCase()));
        ingredient.setTitle(title);
        ingredient.setQuantity(amount);

        return ingredient;
    }

    public Ingredient retrieveEmptyFromJoinQueryResultSet(ResultSet rs) throws SQLException {
        int amount = rs.getInt(RecepitsTable.AMOUNT);
        String title = rs.getString(TableRows.TITLE.toString());
        double price = rs.getDouble(TableRows.PRICE.toString());
        String img = rs.getString(TableRows.IMG.toString());
        String ingredientType = rs.getString(TableRows.TYPE.toString());
        String seasonType = rs.getString(TableRows.SEASON.toString());

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientType(IngredientType.valueOf(ingredientType.toUpperCase()));
        ingredient.setImgPath(img);
        ingredient.setSeasonType(SeasonType.valueOf(seasonType.toUpperCase()));
        ingredient.setTitle(title);
        ingredient.setQuantity(amount);
        ingredient.setCoast(price * amount);

        return ingredient;
    }
}
