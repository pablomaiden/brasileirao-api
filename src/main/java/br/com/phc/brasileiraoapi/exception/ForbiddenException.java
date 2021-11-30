package br.com.phc.brasileiraoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3890528352190562927L;
		
	public ForbiddenException() {
		super();				
	}
	
	public ForbiddenException(String message) {
		super(message);
	}
	
	public ForbiddenException(Throwable cause) {
		super(cause);
	}
	
	public ForbiddenException(String message, Throwable cause) {
		super(message,cause);
	}	

}
