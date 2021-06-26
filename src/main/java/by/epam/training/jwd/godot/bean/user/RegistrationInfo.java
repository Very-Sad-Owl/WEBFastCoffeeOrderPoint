package by.epam.training.jwd.godot.bean.user;

import java.io.Serializable;
import java.util.Objects;

public class RegistrationInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private String email;
	private String hashedPassword;

	public RegistrationInfo(){}

	public RegistrationInfo(String login, String password, String email, String hashedPassword) {
		this.login = login;
		this.password = password;
		this.email = email;
		this.hashedPassword  = hashedPassword;
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

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RegistrationInfo)) return false;
		RegistrationInfo info = (RegistrationInfo) o;
		return Objects.equals(getLogin(), info.getLogin()) &&
				Objects.equals(getPassword(), info.getPassword()) &&
				Objects.equals(getEmail(), info.getEmail()) &&
				Objects.equals(getHashedPassword(), info.getHashedPassword());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLogin(), getPassword(), getEmail(), getHashedPassword());
	}

	@Override
	public String toString() {
		return "RegistrationInfo{" +
				"login='" + login + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", hashedPassword='" + hashedPassword + '\'' +
				'}';
	}
}
