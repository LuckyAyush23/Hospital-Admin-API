package org.jsp.springbootcrud.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpringBootCrudExceptionHandler {
    
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStructure<String>> handle(Exception exception){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage(exception.getMessage());
		structure.setData("Cannot save the data");
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(structure);
	}
}
