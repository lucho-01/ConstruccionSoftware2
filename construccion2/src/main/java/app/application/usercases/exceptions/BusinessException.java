package app.application.usercases.exceptions;

public class BusinessException extends Exception {
	public BusinessException(String message) {
		super(message);
	}
}