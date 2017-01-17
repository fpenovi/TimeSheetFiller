package model.exceptions;

public class CodeDoesNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4888810491504746534L;

	public CodeDoesNotExistException() {
		super();
	}

	public CodeDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CodeDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeDoesNotExistException(String message) {
		super(message);
	}

	public CodeDoesNotExistException(Throwable cause) {
		super(cause);
	}

}
