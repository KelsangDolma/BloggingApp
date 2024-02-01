package com.takeo.exception;


public class InvalidEmailException extends RuntimeException {
	
	private String message;

	public InvalidEmailException(String message) {
		super();
		this.message = message;
	}

	

	
}
