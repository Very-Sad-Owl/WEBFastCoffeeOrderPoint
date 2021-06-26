package by.epam.training.jwd.godot.service.exception;

public class BannedEmailException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public BannedEmailException() {
		super();
	}

	public BannedEmailException(String message) {
		super(message);
	}

	public BannedEmailException(Exception e) {
		super(e);
	}

	public BannedEmailException(String message, Exception e) {
		super(message, e);
	}

}
