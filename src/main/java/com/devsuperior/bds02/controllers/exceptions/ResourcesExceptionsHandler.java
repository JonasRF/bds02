package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DataBaseIntegrityException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;


@ControllerAdvice
public class ResourcesExceptionsHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandarError> EntityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		StandarError err = new StandarError();
		HttpStatus status = HttpStatus.NOT_FOUND;
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resorce not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	
	}
	
	@ExceptionHandler(DataBaseIntegrityException.class)
	public ResponseEntity<StandarError> DataBase(DataBaseIntegrityException e, HttpServletRequest request){
		StandarError err = new StandarError();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
