package by.epam.training.jwd.godot.bean;

import java.time.Month;

public enum OrderStatus {
    NEW("new"), PLACED("placed"), PREPARING("preparing"), READY("ready"), COMPLETED("completed"),
    FORGOTTEN("forgotten"), DENIED("denied");

    private final String status;

    private OrderStatus(String status){
        this.status = status;
    }


    @Override
    public String toString() {
        return status;
    }
}
