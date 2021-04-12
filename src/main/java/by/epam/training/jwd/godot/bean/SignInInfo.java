package by.epam.training.jwd.godot.bean;

import java.io.Serializable;
import java.util.Objects;

public class SignInInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	
	public SignInInfo(){}

	public SignInInfo(String login, String password) {
		this.login = login;
		this.password = password;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SignInInfo)) return false;
		SignInInfo that = (SignInInfo) o;
		return Objects.equals(getLogin(), that.getLogin()) &&
				Objects.equals(getPassword(), that.getPassword());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLogin(), getPassword());
	}

	@Override
	public String toString() {
		return "SignInInfo{" +
				"login='" + login + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
