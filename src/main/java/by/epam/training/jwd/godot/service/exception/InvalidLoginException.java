package by.epam.training.jwd.godot.service.exception;

public class InvalidLoginException extends InvalidUserInfoException{
	private static final long serialVersionUID = 1L;

	public InvalidLoginException() {
		super();
	}

	public InvalidLoginException(String message) {
		super(message);
	}

	public InvalidLoginException(Exception e) {
		super(e);
	}

	public InvalidLoginException(String message, Exception e) {
		super(message, e);
	}

}
