package by.epam.training.jwd.godot.service.exception;

public class RetrievingException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public RetrievingException() {
		super();
	}

	public RetrievingException(String message) {
		super(message);
	}

	public RetrievingException(Exception e) {
		super(e);
	}

	public RetrievingException(String message, Exception e) {
		super(message, e);
	}

}
