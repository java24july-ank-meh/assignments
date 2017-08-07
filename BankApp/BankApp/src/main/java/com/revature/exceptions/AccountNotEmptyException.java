package com.revature.exceptions;

public class AccountNotEmptyException extends Exception {
	private static final long serialVersionUID = 1L;

	public AccountNotEmptyException(String s) {
		super(s);
	}
}
