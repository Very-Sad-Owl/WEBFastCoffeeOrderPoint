package by.epam.training.jwd.godot.service.exception;

public class UpdateException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public UpdateException() {
		super();
	}

	public UpdateException(String message) {
		super(message);
	}

	public UpdateException(Exception e) {
		super(e);
	}

	public UpdateException(String message, Exception e) {
		super(message, e);
	}

}
