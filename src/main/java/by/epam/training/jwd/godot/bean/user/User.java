package by.epam.training.jwd.godot.bean.user;

import java.io.Serializable;
import java.util.Objects;
//TODO: хранить пароль и хэшпароль одновременно зачем-то это что-то на грани нидежды и отчаяния
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String login;
    private String password;
    private String email;
    private UserRole role;
    private String imgPath;

    public User(){}

    public User(String login, String password, String email, UserRole role, String imgPath) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.imgPath = imgPath;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                getRole() == user.getRole() &&
                Objects.equals(getImgPath(), user.getImgPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword(), getEmail(), getRole(), getImgPath());
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
