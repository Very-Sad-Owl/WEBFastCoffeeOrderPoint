package by.epam.training.jwd.godot.service.exception;

public class EmailSendingException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public EmailSendingException() {
		super();
	}

	public EmailSendingException(String message) {
		super(message);
	}

	public EmailSendingException(Exception e) {
		super(e);
	}

	public EmailSendingException(String message, Exception e) {
		super(message, e);
	}

}
