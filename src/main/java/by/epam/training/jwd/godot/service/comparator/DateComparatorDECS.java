package by.epam.training.jwd.godot.service.comparator;

import by.epam.training.jwd.godot.bean.order_element.Order;

import java.util.Comparator;

public class DateComparatorDECS implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return -o1.getDate().compareTo(o2.getDate());
    }
}
