package com.cg.apps.tataskyapp.exception;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
