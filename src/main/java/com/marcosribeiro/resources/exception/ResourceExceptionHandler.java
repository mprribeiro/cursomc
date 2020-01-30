package com.marcosribeiro.resources.exception;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marcosribeiro.services.exceptions.DataIntegrityException;
import com.marcosribeiro.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request)
			throws ParseException {

		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		Date date = new Date(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), sdf.format(date));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class) 
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) throws ParseException {
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), sdf.format(date)); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); 
	}
	 
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) throws ParseException {
	 
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		Date date = new Date(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation Error", sdf.format(date)); 
		for(FieldError x : e.getBindingResult().getFieldErrors()) { 
			err.addError(x.getField(), x.getDefaultMessage()); 
		}
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); 
	}
}
