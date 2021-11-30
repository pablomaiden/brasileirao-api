package br.com.phc.brasileiraoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3890528352190562927L;
		
	public NotFoundException() {
		super();				
	}
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(Throwable cause) {
		super(cause);
	}
	
	public NotFoundException(String message, Throwable cause) {
		super(message,cause);
	}	
	

}
