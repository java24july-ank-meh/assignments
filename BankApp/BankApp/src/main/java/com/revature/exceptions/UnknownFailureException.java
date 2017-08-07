package com.revature.exceptions;

public class UnknownFailureException extends Exception {
	private static final long serialVersionUID = 1L;
	public UnknownFailureException(String s) {
		super(s);
	}
}
