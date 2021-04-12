package by.epam.training.jwd.godot.bean;

public enum PaymentMethod {
    CASH("cash"), CARD("card");

    private final String method;

    private PaymentMethod(String method){
        this.method = method;
    }


    @Override
    public String toString() {
        return method;
    }
}
