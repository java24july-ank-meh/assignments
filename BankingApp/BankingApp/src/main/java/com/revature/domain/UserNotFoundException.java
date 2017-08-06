package com.revature.domain;

public class UserNotFoundException extends RuntimeException{
	
	@Override
	public String getMessage() {
		return "No user in database with given username";
	}
}
