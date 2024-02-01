package com.takeo.exception;


public class InvalidImageFileExtension extends RuntimeException {
	
	private String message;

	public InvalidImageFileExtension(String message) {
		super();
		this.message = message;
	}

	

	
}
