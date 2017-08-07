package com.revature.exceptions;

public class OverdraftException extends Exception {
	private static final long serialVersionUID = 1L;

	public OverdraftException(String s) {
		super(s);
	}
}
