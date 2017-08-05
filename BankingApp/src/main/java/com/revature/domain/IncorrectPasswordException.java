package com.revature.domain;

public class IncorrectPasswordException extends RuntimeException{

	@Override
	public String getMessage() {
		return "Incorrect password for given user";
	}
}
