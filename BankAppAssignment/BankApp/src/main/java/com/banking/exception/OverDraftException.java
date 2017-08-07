package com.banking.exception;

public class OverDraftException extends Exception {
	
	public String getMessage() {
		return "Attempt to overdraft rejected";
	}

}
