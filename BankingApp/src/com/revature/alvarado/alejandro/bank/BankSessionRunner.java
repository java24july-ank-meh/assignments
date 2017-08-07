package com.revature.alvarado.alejandro.bank;

import java.util.Scanner;

import com.revature.alvarado.alejandro.bank.db.BankUserDAOImpl;
import com.revature.alvarado.alejandro.bank.db.UserNotFoundException;

public class BankSessionRunner {

	private BankingSession session;
	private Scanner scanner;
	private BankUserDAOImpl userDAO;
	private String prompt = "Chase Bank> ";
	
	public BankSessionRunner() {
		scanner = new Scanner(System.in);
		userDAO = new BankUserDAOImpl();
	}
	
	// Run the main Banking session
	public void start() {
		
		try {
			this.tryToRegister();
			this.startUserSession();
		} catch (SignInException e) {
			System.out.println(e.getMessage());
			this.closeBankingSession();
			return;
		}
			
		Boolean isLoggedOut = false; 
		do {
			session.printPrompt();
			
			try {
				BankCommands command = BankCommands.valueOf(scanner.next().toUpperCase());
				
				isLoggedOut = session.executeCommand(command);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid Command");
			}
			
		} while (!isLoggedOut);
		
		this.closeBankingSession();	
	}
	
	private void tryToRegister() throws SignInException {
		
		String input = "";
		System.out.println("Are you a returning customer? (Y/N):");
		System.out.print(prompt);
		input = scanner.next();
		
		if (input.equalsIgnoreCase("Y")) {
			return;
		} else if (!input.equalsIgnoreCase("N")) {
			throw new SignInException("Unable to register");
		}
		
		System.out.println("Register as a new user");
		System.out.println("Username: ");
		System.out.print(prompt);
		String name = scanner.next();
		
		System.out.println("Password: ");
		System.out.print(prompt);
		String password = scanner.next();
		
		BankUser user = new BankUser(0, name, false, password);
		
		userDAO.createBankUser(user);
		
	}

	// Get a user from the database and create a new session
	// based on his privileges
	private void startUserSession() throws SignInException {
		
		System.out.println("****** Welcome to Chase Bank ******");
		System.out.println("Please Sign In ...");
		
		System.out.println("Enter Username" );
		System.out.print(prompt);
		String username = scanner.next();
		
		System.out.println("Enter Password");
		System.out.print(prompt);
		String password = scanner.next();
		
		try {
			session = validateUser(username, password);
		} catch (UserNotFoundException e) {
			throw new SignInException("Could not sign into Bank system.");
		}
	
	}
	
	// Close the scanner and give a message to the user
	// that the session is closing
	private void closeBankingSession() {
		
		scanner.close();
		
		System.out.println("Thank you for your time.");
		System.out.println("Closing session...");
	}
	
	// Return a BankUser if the validation was correct, or null if incorrect
	private BankingSession validateUser(String username, String password) 
			throws UserNotFoundException {
				
		/* use a sql query to get a type of user */
		BankUser user = this.userDAO.signInBankUser(username, password);
	
		if (user.getIsAdmin()) {
			return new SuperUserBankingSession(user);
		} else {
			return new UserBankingSession(user);
		}
	}
	
}
