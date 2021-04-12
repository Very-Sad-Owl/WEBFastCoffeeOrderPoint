package by.epam.training.jwd.godot.service.exception;

import java.util.ArrayList;
import java.util.List;

public class InvalidUserInfoException extends ServiceException{
	private static final long serialVersionUID = 1L;
	private List<InvalidUserInfoException> exceptions = new ArrayList<>();

	public InvalidUserInfoException() {
		super();
	}

	public InvalidUserInfoException(String message) {
		super(message);
	}

	public InvalidUserInfoException(Exception e) {
		super(e);
	}

	public InvalidUserInfoException(String message, Exception e) {
		super(message, e);
	}

	public void addCause(InvalidUserInfoException cause){
		exceptions.add(cause);
	}

	public List<InvalidUserInfoException> getCauses(){
		return exceptions;
	}

}
