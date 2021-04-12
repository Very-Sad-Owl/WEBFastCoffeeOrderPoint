package by.epam.training.jwd.godot.bean;

public enum UserRole {
    GUEST(1), USER(2), ADMIN(3), BANNED(4);

    private final int id;

    UserRole(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
