package com.module01.clientCRUD.resources.expetions;

import java.io.Serializable;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.module01.clientCRUD.servicies.expetions.DataBaseExpetion;
import com.module01.clientCRUD.servicies.expetions.EntityNotFoundExpetions;

@RestControllerAdvice
public class ExeptionsHandler implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(EntityNotFoundExpetions.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundExpetions e, HttpServletRequest request){
		StandardError err = new StandardError();
		HttpStatus status = HttpStatus.NOT_FOUND;
		err.setError("Request not found");
		err.setMsg(e.getMessage());
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setStatus(status.value());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataBaseExpetion.class)
	public ResponseEntity<StandardError> dataBase(DataBaseExpetion e, HttpServletRequest request){
		StandardError err = new StandardError();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		err.setError("DataBase Expetion");
		err.setMsg(e.getMessage());
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setStatus(status.value());
		return ResponseEntity.status(status).body(err);
	}
	
}
