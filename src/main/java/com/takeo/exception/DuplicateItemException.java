package com.takeo.exception;


public class DuplicateItemException extends RuntimeException{

	private String message;

	
	public DuplicateItemException(String message) {
		super();
		this.message = message;
	}


	
}
