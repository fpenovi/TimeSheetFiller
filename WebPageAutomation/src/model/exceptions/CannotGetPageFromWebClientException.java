package model.exceptions;

public class CannotGetPageFromWebClientException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6498846002434786856L;
	private Exception origin;
	

	public CannotGetPageFromWebClientException(Exception e) {
		this.origin = e;
	}
	
	
	@Override
	public String getMessage() {
		return this.origin.getMessage();
	}
}
