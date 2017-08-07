package com.revature.exceptions;

public class ExistingUserException extends Exception {
	private static final long serialVersionUID = 1L;

	public ExistingUserException(String s) {
		super(s);
	}
}
