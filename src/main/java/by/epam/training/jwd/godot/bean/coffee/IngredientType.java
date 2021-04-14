package by.epam.training.jwd.godot.bean.coffee;

import by.epam.training.jwd.godot.bean.coffee.Ingredient;

public enum IngredientType {
    MAIN("main"), DECORATION("decoration"), UNIVERSAL("universal");

    private final String type;

    private IngredientType(String type){
        this.type = type;
    }


    @Override
    public String toString() {
        return type;
    }
}
