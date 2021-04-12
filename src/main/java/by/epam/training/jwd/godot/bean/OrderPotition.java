package by.epam.training.jwd.godot.bean;

import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;

import java.io.Serializable;
import java.util.Objects;

public class OrderPotition implements Serializable {
    private static final long serialVersionUID = 1L;

    private long uid;
    private Coffee beverage;
    private int amount;
    private double coast = 0;

    public OrderPotition(){}

    public OrderPotition(long uid, Coffee beverage, int amount, double coast) {
        this.uid = uid;
        this.beverage = beverage;
        this.amount = amount;
        this.coast = coast;
    }

    public String getDesctiption() {
        StringBuilder description = new StringBuilder();
        description.append(beverage.getType()).append(" ");
        for (Ingredient decor : beverage.getDecorations()) {
            description.append(decor).append(" ");

        }
        return description.toString();
    }


    public Coffee getBeverage() {
        return beverage;
    }

    public void setBeverage(Coffee beverage) {
        this.beverage = beverage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getCoast() {
        return coast;
    }

    public void setCoast(double coast) {
        this.coast = coast;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public double calculateCoast(){
        return beverage.getCoast() * amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderPotition)) return false;
        OrderPotition that = (OrderPotition) o;
        return getAmount() == that.getAmount() &&
                Double.compare(that.getCoast(), getCoast()) == 0 &&
                Objects.equals(getBeverage(), that.getBeverage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBeverage(), getAmount(), getCoast());
    }

    @Override
    public String toString() {
        return "OrderPotition{" +
                "beverage=" + beverage +
                ", amount=" + amount +
                ", coast=" + coast +
                '}';
    }
}
