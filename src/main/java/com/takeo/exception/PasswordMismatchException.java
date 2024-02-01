package com.takeo.exception;




public class PasswordMismatchException extends RuntimeException {
	
	private String message;

	public PasswordMismatchException(String message) {
		super();
		this.message = message;
	}

	

}
