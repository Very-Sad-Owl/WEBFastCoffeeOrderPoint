package by.epam.training.jwd.godot.service.exception;

public class InvalidPasswordException extends InvalidUserInfoException{
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
		super();
	}

	public InvalidPasswordException(String message) {
		super(message);
	}

	public InvalidPasswordException(Exception e) {
		super(e);
	}

	public InvalidPasswordException(String message, Exception e) {
		super(message, e);
	}

}
