package com.module01.clientCRUD.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.module01.clientCRUD.services.exceptions.DataBaseException;
import com.module01.clientCRUD.services.exceptions.EntityNotFoundExceptions;
//import com.module01.clientCRUD.services.exceptions.DataBaseExpetion;

@RestControllerAdvice
public class ExceptionsHandler implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(EntityNotFoundExceptions.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundExceptions e, HttpServletRequest request){
		StandardError err = new StandardError();
		HttpStatus status = HttpStatus.NOT_FOUND;
		err.setError("Request not found");
		err.setMsg(e.getMessage());
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setStatus(status.value());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request){
		StandardError err = new StandardError();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		err.setError("DataBase Exception");
		err.setMsg(e.getMessage());
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setStatus(status.value());
		return ResponseEntity.status(status).body(err);
	}
	
}
