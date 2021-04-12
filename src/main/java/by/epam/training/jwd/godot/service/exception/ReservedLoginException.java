package by.epam.training.jwd.godot.service.exception;

public class ReservedLoginException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public ReservedLoginException() {
		super();
	}

	public ReservedLoginException(String message) {
		super(message);
	}

	public ReservedLoginException(Exception e) {
		super(e);
	}

	public ReservedLoginException(String message, Exception e) {
		super(message, e);
	}

}
