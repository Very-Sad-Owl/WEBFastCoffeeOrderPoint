package by.epam.training.jwd.godot.service.comparator;

import by.epam.training.jwd.godot.bean.order_element.Order;

import java.util.Comparator;

public class UserComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getUser().getLogin().compareTo(o2.getUser().getLogin());
    }
}
