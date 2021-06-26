package by.epam.training.jwd.godot.service.comparator;

import by.epam.training.jwd.godot.bean.order_element.Order;

import java.util.Comparator;

public class UidComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getUid() > o2.getUid()){
            return 1;
        } else if (o1.getUid() < o2.getUid()){
            return -1;
        }
        return 0;
    }
}
