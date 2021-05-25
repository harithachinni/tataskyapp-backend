package com.cg.apps.tataskyapp.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(AccountNotFoundException.class)
	public String handleAccountNotFound(AccountNotFoundException e) {
		return e.getMessage();
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	public String handleUserNotFound(UserNotFoundException e) {
		return e.getMessage();
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(PackNotFoundException.class)
	public String handlePackNotFound(PackNotFoundException e) {
		return e.getMessage();
	}

	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(RequestNotFoundException.class)
	public String handleRequestNotFound(RequestNotFoundException e) {
		return e.getMessage();
	}
	@ExceptionHandler(RechargeException.class)
	public String handleRechargeException(RechargeException e) {
		return e.getMessage();
	}
}
