package br.com.phc.brasileiraoapi.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{
		
	@ExceptionHandler(BadRequestionException.class)
	public ResponseEntity<StandartError> badRequestException(BadRequestionException e, HttpServletRequest request){		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			   .body(new StandartError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI()));
		
	}	
	
	@ExceptionHandler(UnathorizedException.class)
	public ResponseEntity<StandartError> badRequestException(UnathorizedException e, HttpServletRequest request){		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			   .body(new StandartError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI()));
		
	}	

}
