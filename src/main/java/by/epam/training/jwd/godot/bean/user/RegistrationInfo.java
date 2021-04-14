package by.epam.training.jwd.godot.bean.user;

import java.io.Serializable;
import java.util.Objects;

public class RegistrationInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private String email;

	public RegistrationInfo(){}

	public RegistrationInfo(String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RegistrationInfo)) return false;
		RegistrationInfo that = (RegistrationInfo) o;
		return Objects.equals(getLogin(), that.getLogin()) &&
				Objects.equals(getPassword(), that.getPassword()) &&
				Objects.equals(getEmail(), that.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLogin(), getPassword(), getEmail());
	}

	@Override
	public String toString() {
		return "RegistrationInfo{" +
				"login='" + login + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
