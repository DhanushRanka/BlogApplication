package com.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethod(MethodArgumentNotValidException ex)
	{
		Map<String,String>mp=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(e->{			
			String fieldName=((FieldError)e).getField();
			String defaultMessage = e.getDefaultMessage();
			mp.put("fieldName", fieldName);
			mp.put("defaultMessage", defaultMessage);
		});
		
		return new ResponseEntity<Map<String,String>>(mp, HttpStatus.BAD_REQUEST);
	}
	
}
