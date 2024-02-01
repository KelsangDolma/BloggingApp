package com.takeo.exception;


public class InvalidPasswordException extends RuntimeException {

	private String message;

	
	public InvalidPasswordException(String message) {
		super();
		this.message = message;
	}


}
