package by.epam.training.jwd.godot.service.exception;

public class InvalidEmailException extends InvalidUserInfoException{
	private static final long serialVersionUID = 1L;

	public InvalidEmailException() {
		super();
	}

	public InvalidEmailException(String message) {
		super(message);
	}

	public InvalidEmailException(Exception e) {
		super(e);
	}

	public InvalidEmailException(String message, Exception e) {
		super(message, e);
	}

}
