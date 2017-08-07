package com.revature.alvarado.alejandro.bank;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.alvarado.alejandro.bank.db.AccountNotFoundException;
import com.revature.alvarado.alejandro.bank.db.BankAccountDAOImpl;

public class UserBankingSession implements BankingSession {

	protected BankUser user;
	protected Scanner scanner;
	private BankAccountDAOImpl accountDAO;
	private String prompt = "user> ";
		
	public UserBankingSession(BankUser user) {
		this.user = user;
		scanner = new Scanner(System.in);
		accountDAO = new BankAccountDAOImpl();
	}
	
	@Override
	public Boolean executeCommand(BankCommands command) {
		
		switch(command) {
		
		case VIEWACCOUNTS:
			this.viewAccounts();
			break;
		case CREATEACCOUNT:
			this.createAccount();
			break;
		case DELETEACCOUNT:
			this.deleteAccount();
			break;
		case DEPOSIT:
			this.deposit();
			break;
		case WITHDRAW:
			this.withdraw();
			break;
		case LOGOUT:
			return this.logout();
		case HELP:
			this.printHelpInstructions();
			break;
		default:
			System.out.println("Unkown Command");
		}
		
		return false;
	}

	private void endPrompt(String command, Boolean success) {
		if (success) {
			System.out.println(command + " was successful");
		} else {
			System.out.println(command + " was not successful");
		}
	}
	
	protected Boolean logout() {
		System.out.println("Logging out!");
		return true;
	}

	private void withdraw() {
		
		try {
			System.out.println("Account: ");
			System.out.print(prompt);
			Integer accountId = scanner.nextInt();
			
			System.out.println("Amount: ");
			System.out.print(prompt);
			Integer amount = scanner.nextInt();
			
			if (amount < 0) {
				throw new InputMismatchException();
			}
			
			accountDAO.updateBankAccount(user.getUserId(), accountId, -amount);
		} catch (AccountNotFoundException e) {
			System.out.println("Couldn't find that account.");
			this.endPrompt("Withdraw", false);
			return;
		} catch (NotEnoughFundsException e) {
			System.out.println("Not enough funds in your account.");
			this.endPrompt("Withdraw", false);
			return;
		} catch (InputMismatchException e) {
			System.out.println("Input was incorrect");
			this.endPrompt("Withdraw", false);
			return;
		}
		
		this.endPrompt("Withdraw", true);
	}

	private void deposit() {		
		try {
			System.out.println("Account: ");
			System.out.print(prompt);
			Integer accountId = scanner.nextInt();
			
			System.out.println("Amount: ");
			System.out.print(prompt);
			Integer amount = scanner.nextInt();
			
			
			if (amount < 0) {
				throw new InputMismatchException();
			}
					
			accountDAO.updateBankAccount(user.getUserId(), accountId, amount);
		} catch (AccountNotFoundException e) {
			System.out.println("Couldn't find that account.");
			this.endPrompt("Deposit", false);
			return;
		} catch (InputMismatchException e) {
			System.out.println("Inputs were incorrect");
			this.endPrompt("Deposit", false);
			return;
		} catch (NotEnoughFundsException e) {
			e.printStackTrace();
			return;
		}
		
		this.endPrompt("Deposit", true);
	}

	private void deleteAccount() {
	
		try {
			System.out.println("Account: ");
			System.out.print(prompt);
			Integer accountId = scanner.nextInt();
			
			accountDAO.deleteBankAccount(user.getUserId(), accountId);
		} catch (AccountNotFoundException e) {
			System.out.println("Couldn't find that account.");
			this.endPrompt("Delete", false);
		} catch (InputMismatchException e) {
			System.out.println("Input was incorrect");
			this.endPrompt("Delete", false);
			return;
		}
		
		this.endPrompt("Delete", true);
	}

	private void createAccount() {
		
		try {
			System.out.println("Creating an account...");
			
			accountDAO.createBankAccount(user.getUserId());
			
		} catch(InputMismatchException e) {
			System.out.println("Incorrect input.");
			this.endPrompt("Create Account", false);
			return;
		}
		this.endPrompt("Create Account", true);
	}

	private void viewAccounts() {
		
		System.out.println("Viewing accounts...");
		
		List<BankAccount> accounts = accountDAO.getAllBankAccountsForUser(user.getUserId());
		
		for (BankAccount account : accounts) {
			System.out.println();
			System.out.println("Account number: " + account.getAccountId());
			System.out.println("Account balance: " + account.getAmount());
		}
		
	}

	@Override
	public void printPrompt() {
		System.out.print(prompt);
	}
	
	private void printHelpInstructions() {
		System.out.println();
		System.out.println("User Commands List");
		System.out.println("View Accounts     (VIEWACCOUNTS)");
		System.out.println("Create an account (CREATEACCOUNT)");
		System.out.println("Delete an account (DELETEACCOUNT)");
		System.out.println("Deposit money     (DEPOSIT)");
		System.out.println("Withdraw money    (WITHDRAW)");
		System.out.println("Logout            (LOGOUT)");
		System.out.println("Help              (HELP)");
	}

}
