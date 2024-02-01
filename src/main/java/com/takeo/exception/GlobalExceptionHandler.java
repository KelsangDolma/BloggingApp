package com.takeo.exception;

import java.util.Date;


import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.takeo.exception.InvalidEmailException;
import com.takeo.exception.InvalidPasswordException;



@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);

		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(InvalidEmailException.class)
	protected ResponseEntity<ApiResponse> invalidEmailException(InvalidEmailException e) {
		ApiResponse res = ApiResponse.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST)
				.timestamp(new Date()).build();
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidPasswordException.class)
	protected ResponseEntity<ApiResponse> invalidPasswordException(InvalidPasswordException e) {
		ApiResponse res = ApiResponse.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST)
				.timestamp(new Date()).build();
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(PasswordMismatchException.class)
	protected ResponseEntity<ApiResponse> passwordMismatchException(PasswordMismatchException e) {
		ApiResponse res = ApiResponse.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST)
				.timestamp(new Date()).build();
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException e) {
		ApiResponse res = ApiResponse.builder().message(e.getMessage()).status(HttpStatus.NOT_FOUND)
				.timestamp(new Date()).build();
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(InvalidImageFileExtension.class)
	protected ResponseEntity<ApiResponse> invalidFileExtensionException(InvalidImageFileExtension e) {
		ApiResponse res = ApiResponse.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST)
				.timestamp(new Date()).build();
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

}
