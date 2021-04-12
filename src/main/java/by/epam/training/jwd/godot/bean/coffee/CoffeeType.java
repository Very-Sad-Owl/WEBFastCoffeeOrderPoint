package by.epam.training.jwd.godot.bean.coffee;

public enum CoffeeType {
    ESPRESSO("ESPRESSO"),
    LATTE("latte"),
    CAPPUCHINO("cappuchino");

    private final String type;

    private CoffeeType(String type){
        this.type = type;
    }


    @Override
    public String toString() {
        return type;
    }
}
