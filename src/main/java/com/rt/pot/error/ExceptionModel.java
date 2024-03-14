package com.rt.pot.error;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ExceptionModel {

	
	private String message;
	private HttpStatus httpStatus;
}
