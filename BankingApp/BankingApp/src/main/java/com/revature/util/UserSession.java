package com.revature.util;

import java.util.Scanner;

import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;
import com.revature.domain.Account;
import com.revature.domain.AccountTypes;
import com.revature.domain.BankUser;

public class UserSession {

	private static Object lock = new Object();
	private static UserSession userSession;
	private static BankDAO bankdao = new BankDAOImpl();
	private BankUser user;
	private Scanner s;
	
	private UserSession(BankUser user) {
		this.user = user;
		s = new Scanner(System.in);
	}
	
	public static UserSession startUserSession(BankUser b) {
		
		synchronized(lock) {
			if(userSession==null) {
				userSession = new UserSession(b);
			}
		}
		bankdao.viewMyAccounts(userSession.user);
		return userSession;
	}
	
	public static void login() {
		
		bankdao.login(userSession.user);
	}
	
	public static void logout() {
		
		bankdao.logout(userSession.user);
	}
	
	public void sessionLoop() {
		if(!bankdao.isLoggedIn(user)) {
			System.out.println("Login failed. Failed to update LOGGEDIN field in BANKUSERS table");
			return;
		}
		System.out.println("You are now logged in!");
		sessionLoop:while(true) {
			System.out.println("Type next command");
			String input = s.nextLine();
			if(input.equalsIgnoreCase("logout")) {
				bankdao.logout(user);
				System.out.println("\nCome again soon!\n");
				break sessionLoop;
			}else if(input.equalsIgnoreCase("deposit")) {
				transactionLoop(true);
			}else if(input.equalsIgnoreCase("withdraw")) {
				transactionLoop(false);
			}else if(input.equalsIgnoreCase("create")) {
				newAccount();
			}else if(input.equalsIgnoreCase("delete")) {
				deleteAccount();
			}
			
		}
	}
	
	public void transactionLoop(boolean deposit) {
		if(user.getAccounts().size()<1) {
			System.out.println("You must create an account.");
			return;
		}
		System.out.println("Choose an account id.");
		for(Account a : user.getAccounts()) {
			System.out.println(a);
		}
		int id = s.nextInt();
		Account a = user.getAccountFromId(id);
		double balance = bankdao.viewBalance(user, a);
		System.out.println("Current balance is " + balance);
		System.out.print("Enter amount to ");
		if(deposit) {System.out.println("deposit");}
		else {System.out.println("withdraw");}
		double amt = s.nextDouble();
		if(!deposit) {amt = -amt;}
		bankdao.transaction(user, a, amt);
		System.out.println("success");
		
	}
	
	public void newAccount() {
		System.out.println("Account Type?");
		String accountType = s.nextLine().toUpperCase();
		System.out.println("Interest Rate?");
		double interestRate = s.nextDouble();
		System.out.println("Initial Balance?");
		double initialBalance = s.nextDouble();
		
		Account newAcct = new Account(user, initialBalance, accountType, interestRate);
		bankdao.createAccount(user, newAcct);
		user.addAccount(newAcct);
		System.out.println("You have a new account!");
		return;
	}
	
	public void deleteAccount() {
		System.out.println("Choose an account id.");
		for(Account a : user.getAccounts()) {
			System.out.println(a);
		}
		int id = s.nextInt();
		Account a = user.getAccountFromId(id);
		if(bankdao.viewBalance(user, a)>0.0) {
			System.out.println("Account balance must be 0.0. Try again."); return;
		}
		bankdao.deleteAccount(user, a);
	}
	
	
}
