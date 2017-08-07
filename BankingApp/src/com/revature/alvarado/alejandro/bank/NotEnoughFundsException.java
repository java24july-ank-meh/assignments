package com.revature.alvarado.alejandro.bank;

public class NotEnoughFundsException extends Exception {

	private static final long serialVersionUID = -896028965959531432L;

	public NotEnoughFundsException(String message) {
		super(message);
	}
}
