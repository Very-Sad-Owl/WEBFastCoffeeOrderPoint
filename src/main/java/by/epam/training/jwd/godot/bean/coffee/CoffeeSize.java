package by.epam.training.jwd.godot.bean.coffee;

import java.io.Serializable;
import java.util.Objects;

public class CoffeeSize implements Serializable {

    private static final long serialVersionUID = 1L;
    private int volume;
    private double increment;

    public CoffeeSize(){}

    public CoffeeSize(int volume, double increment) {
        this.volume = volume;
        this.increment = increment;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getIncrement() {
        return increment;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoffeeSize)) return false;
        CoffeeSize that = (CoffeeSize) o;
        return getVolume() == that.getVolume() &&
                Double.compare(that.getIncrement(), getIncrement()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVolume(), getIncrement());
    }

    @Override
    public String toString() {
        return "CoffeeSize{" +
                "volume=" + volume +
                ", increment=" + increment +
                '}';
    }
}
