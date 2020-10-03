package com.example.demo.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.dto.ErrorDTO;

@ControllerAdvice
public class HandleExceptions {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleAllUserNotFound(UserNotFoundException ex,
			final HttpServletRequest httpServletRequest) {

		ErrorDTO error = new ErrorDTO();
		error.setMessage(ex.getMessage());
		error.setRequest(httpServletRequest.getRequestURL().toString());
		error.setStatus(HttpStatus.BAD_REQUEST.name());

		return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleAllExceptions(Exception ex, final HttpServletRequest httpServletRequest) {

		ErrorDTO error = new ErrorDTO();

		error.setMessage(ex.getMessage());
		error.setRequest(httpServletRequest.getRequestURL().toString());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());

		return new ResponseEntity<ErrorDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
