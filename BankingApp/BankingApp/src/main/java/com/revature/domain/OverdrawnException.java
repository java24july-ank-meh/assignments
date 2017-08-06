package com.revature.domain;

public class OverdrawnException extends RuntimeException{

	@Override
	public String getMessage() {
		return "Account Overdrawn";
	}
}
