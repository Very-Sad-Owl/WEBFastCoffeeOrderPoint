package by.epam.training.jwd.godot.service.exception;

public class InsertionException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public InsertionException() {
		super();
	}

	public InsertionException(String message) {
		super(message);
	}

	public InsertionException(Exception e) {
		super(e);
	}

	public InsertionException(String message, Exception e) {
		super(message, e);
	}

}
