package by.epam.training.jwd.godot.bean.user;

public enum UserRole {
    USER(2), ADMIN(3), BANNED(4), UNVERIFIED(5);

    private final int id;

    UserRole(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
