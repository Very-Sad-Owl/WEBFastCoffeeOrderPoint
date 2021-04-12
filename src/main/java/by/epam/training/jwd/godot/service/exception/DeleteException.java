package by.epam.training.jwd.godot.service.exception;

public class DeleteException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public DeleteException() {
		super();
	}

	public DeleteException(String message) {
		super(message);
	}

	public DeleteException(Exception e) {
		super(e);
	}

	public DeleteException(String message, Exception e) {
		super(message, e);
	}

}
