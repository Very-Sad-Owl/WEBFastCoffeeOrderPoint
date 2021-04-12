package by.epam.training.jwd.godot.service.exception;

public class NoSuchUserException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public NoSuchUserException() {
		super();
	}

	public NoSuchUserException(String message) {
		super(message);
	}

	public NoSuchUserException(Exception e) {
		super(e);
	}

	public NoSuchUserException(String message, Exception e) {
		super(message, e);
	}

}
