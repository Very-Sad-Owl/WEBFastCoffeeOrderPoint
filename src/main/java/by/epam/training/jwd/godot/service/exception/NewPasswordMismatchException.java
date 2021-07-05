package by.epam.training.jwd.godot.service.exception;

public class NewPasswordMismatchException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public NewPasswordMismatchException() {
		super();
	}

	public NewPasswordMismatchException(String message) {
		super(message);
	}

	public NewPasswordMismatchException(Exception e) {
		super(e);
	}

	public NewPasswordMismatchException(String message, Exception e) {
		super(message, e);
	}

}
