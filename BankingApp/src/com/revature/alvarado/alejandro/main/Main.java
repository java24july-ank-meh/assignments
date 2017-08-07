package com.revature.alvarado.alejandro.main;

import com.revature.alvarado.alejandro.bank.BankSessionRunner;

public class Main {
		
	// Main runner for the application
	public static void main(String[] args) {
		
		BankSessionRunner session = new BankSessionRunner();
		
		session.start();
	}
	
}
