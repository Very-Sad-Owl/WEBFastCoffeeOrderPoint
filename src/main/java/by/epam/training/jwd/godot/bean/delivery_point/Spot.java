package by.epam.training.jwd.godot.bean.delivery_point;

import java.io.Serializable;
import java.util.Objects;

public class Spot implements Serializable {
    private static final long serialVersionUID = 1L;

    private long uid;
    private double rating;
    private double balance;
    private Address address;

    public Spot(){}

    public Spot(long uid, double rating, double balance, Address address) {
        this.uid = uid;
        this.rating = rating;
        this.balance = balance;
        this.address = address;
    }

    public Spot(double rating, double balance, Address address) {
        this.rating = rating;
        this.balance = balance;
        this.address = address;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spot)) return false;
        Spot spot = (Spot) o;
        return getUid() == spot.getUid() &&
                Double.compare(spot.getRating(), getRating()) == 0 &&
                Double.compare(spot.getBalance(), getBalance()) == 0 &&
                Objects.equals(getAddress(), spot.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getRating(), getBalance(), getAddress());
    }

    @Override
    public String toString() {
        return "Spot{" +
                "uid=" + uid +
                ", rating=" + rating +
                ", balance=" + balance +
                ", address=" + address +
                '}';
    }
}
