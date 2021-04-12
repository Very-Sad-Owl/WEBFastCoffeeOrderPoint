package by.epam.training.jwd.godot.bean.coffee;

import by.epam.training.jwd.godot.bean.IngredientType;
import by.epam.training.jwd.godot.bean.SeasonType;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private int quantity;
    private double coast;
    private IngredientType ingredientType;
    private String imgPath;
    private SeasonType seasonType;

    public Ingredient(){}

    public Ingredient(String title, int quantity, double coast, IngredientType ingredientType, String imgPath, SeasonType seasonType) {
        this.title = title;
        this.quantity = quantity;
        this.coast = coast;
        this.ingredientType = ingredientType;
        this.imgPath = imgPath;
        this.seasonType = seasonType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCoast() {
        return coast;
    }

    public void setCoast(double coast) {
        this.coast = coast;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public SeasonType getSeasonType() {
        return seasonType;
    }

    public void setSeasonType(SeasonType seasonType) {
        this.seasonType = seasonType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return getQuantity() == that.getQuantity() &&
                Double.compare(that.getCoast(), getCoast()) == 0 &&
                Objects.equals(getTitle(), that.getTitle()) &&
                getIngredientType() == that.getIngredientType() &&
                Objects.equals(getImgPath(), that.getImgPath()) &&
                getSeasonType() == that.getSeasonType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getQuantity(), getCoast(), getIngredientType(), getImgPath(), getSeasonType());
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "title='" + title + '\'' +
                ", quantity=" + quantity +
                ", coast=" + coast +
                ", ingredientType=" + ingredientType +
                ", imgPath='" + imgPath + '\'' +
                ", seasonType=" + seasonType +
                '}';
    }
}
