package br.com.phc.brasileiraoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3890528352190562927L;
		
	public InternalServerException() {
		super();				
	}
	
	public InternalServerException(String message) {
		super(message);
	}
	
	public InternalServerException(Throwable cause) {
		super(cause);
	}
	
	public InternalServerException(String message, Throwable cause) {
		super(message,cause);
	}	
	

}
