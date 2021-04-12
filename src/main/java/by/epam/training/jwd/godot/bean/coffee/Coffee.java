package by.epam.training.jwd.godot.bean.coffee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coffee implements Serializable {

    private static final long serialVersionUID = 1L;
    private CoffeeType type;
    private String description;
    private double coast;
    private String imgPath;
    private CoffeeSize size;
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Ingredient> decorations = new ArrayList<>();

    public Coffee(){}

    public Coffee(CoffeeType type, String description, double coast, String imgPath) {
            this.type = type;
        this.description = description;
        this.coast = coast;
        this.imgPath = imgPath;
    }

    public Coffee(CoffeeType type, String imgPath) {
        this.type = type;
        this.imgPath = imgPath;
    }

    public Coffee(CoffeeType type, double coast, String description, String imgPath) {
        this.type = type;
        this.coast = coast;
        this.description = description;
        this.imgPath = imgPath;
    }

    public Coffee(CoffeeType type, String imgPath, CoffeeSize size) {
        this.type = type;
        this.imgPath = imgPath;
        this.size = size;
    }

    public Coffee(CoffeeType type, String description, double coast, String imgPath,
                  CoffeeSize size, List<Ingredient> ingredients) {
        this.type = type;
        this.description = description;
        this.coast = coast;
        this.imgPath = imgPath;
        this.size = size;
        this.ingredients = ingredients;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCoast() {
        return coast;
    }

    public void setCoast(double coast) {
        this.coast = coast;
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public CoffeeSize getSize() {
        return size;
    }

    public void setSize(CoffeeSize size) {
        this.size = size;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addDecoration(Ingredient decoration){
        decorations.add(decoration);
    }

    public void addDecorations(List<Ingredient> newDecorations){
        decorations.addAll(newDecorations);
    }

    public void removeDecoration(Ingredient decoration){
        decorations.remove(decoration);
    }

    public List<Ingredient> getDecorations() {
        return decorations;
    }

    public void setDecorations(List<Ingredient> decorations) {
        this.decorations = decorations;
    }

    public double calculateCoast(){
        double coast = 0;
        for (Ingredient el : ingredients){
            coast += el.getCoast();
        }

        for (Ingredient el : decorations){
            coast += el.getCoast();
        }
        return coast *= size != null ? size.getIncrement() : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coffee)) return false;
        Coffee coffee = (Coffee) o;
        return Double.compare(coffee.getCoast(), getCoast()) == 0 &&
                getType() == coffee.getType() &&
                Objects.equals(getDescription(), coffee.getDescription()) &&
                Objects.equals(getImgPath(), coffee.getImgPath()) &&
                Objects.equals(getSize(), coffee.getSize()) &&
                Objects.equals(getIngredients(), coffee.getIngredients()) &&
                Objects.equals(decorations, coffee.decorations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getDescription(), getCoast(), getImgPath(), getSize(), getIngredients(), decorations);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "type=" + type +
                ", description='" + description + '\'' +
                ", coast=" + coast +
                ", imgPath='" + imgPath + '\'' +
                ", size=" + size +
                ", ingredients=" + ingredients +
                ", decorations=" + decorations +
                '}';
    }
}
