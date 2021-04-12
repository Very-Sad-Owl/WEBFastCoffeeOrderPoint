package by.epam.training.jwd.godot.bean;

import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private long uid;
    private User user;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private List<OrderPotition> positions = new ArrayList<>();
    private Spot address;
    private double coast = 0;
    private int estimatedTime;

    public Order(){}

    public Order(long uid, User user, OrderStatus status, PaymentMethod paymentMethod, List<OrderPotition> positions, Spot address, double coast, int estimatedTime) {
        this.uid = uid;
        this.user = user;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.positions = positions;
        this.address = address;
        this.coast = coast;
        this.estimatedTime = estimatedTime;
    }

    public Order(User user, OrderStatus status) {
        this.user = user;
        this.status = status;
    }

    private double calculateCoast(){
        double coast = 0;
        for (OrderPotition el : positions){
            coast += el.getCoast();
        }
        return coast;
    }

    public String getDesctiption(){
        StringBuilder description = new StringBuilder();
        for (OrderPotition el : positions){
            description.append(el.getBeverage().getType()).append(" ");
            for (Ingredient decor : el.getBeverage().getDecorations()){
                description.append(decor).append(" ");
            }
        }
        return description.toString();
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderPotition> getPositions() {
        return positions;
    }

    public void setPositions(List<OrderPotition> positions) {
        this.positions = positions;
    }

    public Spot getAddress() {
        return address;
    }

    public void setAddress(Spot address) {
        this.address = address;
    }

    public double getCoast() {
        return coast;
    }

    public void setCoast(double coast) {
        this.coast = coast;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getUid() == order.getUid() &&
                Double.compare(order.getCoast(), getCoast()) == 0 &&
                getEstimatedTime() == order.getEstimatedTime() &&
                Objects.equals(getUser(), order.getUser()) &&
                getStatus() == order.getStatus() &&
                getPaymentMethod() == order.getPaymentMethod() &&
                Objects.equals(getPositions(), order.getPositions()) &&
                Objects.equals(getAddress(), order.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getUser(), getStatus(), getPaymentMethod(), getPositions(), getAddress(), getCoast(), getEstimatedTime());
    }

    @Override
    public String toString() {
        return "Order{" +
                "uid=" + uid +
                ", user=" + user +
                ", status=" + status +
                ", paymentMethod=" + paymentMethod +
                ", positions=" + positions +
                ", address=" + address +
                ", coast=" + coast +
                ", estimatedTime=" + estimatedTime +
                '}';
    }
}
