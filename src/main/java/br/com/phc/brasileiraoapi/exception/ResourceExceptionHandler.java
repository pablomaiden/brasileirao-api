package br.com.phc.brasileiraoapi.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			   .body(new StandartError(HttpStatus.UNAUTHORIZED, e.getMessage(), request.getRequestURI()));
		
	}	
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<StandartError> badRequestException(ForbiddenException e, HttpServletRequest request){		
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
			   .body(new StandartError(HttpStatus.FORBIDDEN, e.getMessage(), request.getRequestURI()));
		
	}	
	
	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<StandartError> badRequestException(InternalServerException e, HttpServletRequest request){		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			   .body(new StandartError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI()));
		
	}	
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandartError> badRequestException(NotFoundException e, HttpServletRequest request){		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			   .body(new StandartError(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI()));
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<StandartError> badRequestException(RuntimeException e, HttpServletRequest request){		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			   .body(new StandartError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI()));
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception e, Object body, HttpStatus httpStatus, 
			HttpHeaders httpHeaders, WebRequest webRequest){		
		return ResponseEntity.status(httpStatus)
				   .body(new StandartError(httpStatus, e.getMessage(), webRequest.getContextPath()));
		
	}
	

}
