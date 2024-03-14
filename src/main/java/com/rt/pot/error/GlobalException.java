package com.rt.pot.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(PotException.class)
	private ResponseEntity<ExceptionModel> potExceptionHandler(Exception exception){
		
		ExceptionModel exceptionModel = new ExceptionModel();
		exceptionModel.setMessage(exception.getMessage());
		exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(exceptionModel,HttpStatus.BAD_REQUEST);
		
	}
	
}
