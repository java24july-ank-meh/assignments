package com.revature.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;
import com.revature.domain.Account;
import com.revature.domain.AccountTypes;
import com.revature.domain.BankUser;
import com.revature.domain.SuperUser;

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
		loop:while(true) {
			System.out.println("\nType next command");
			String input = s.nextLine();
			if(input.equalsIgnoreCase("logout")) {
				bankdao.logout(user);
				System.out.println("\nCome again soon!\n");
				break loop;
			}else if(input.equalsIgnoreCase("deposit")) {
				transactionLoop(true);
			}else if(input.equalsIgnoreCase("withdraw")) {
				transactionLoop(false);
			}else if(input.equalsIgnoreCase("create")) {
				newAccount();
			}else if(input.equalsIgnoreCase("delete")) {
				deleteAccount();
			}else if(input.equalsIgnoreCase("my accounts")) {
				List<Account> myAccounts = bankdao.viewMyAccounts(user);
				for(Account a : myAccounts) {
					System.out.println(a);
				}
			}
			else if(input.equalsIgnoreCase("delete user")) {
				if(!(user instanceof SuperUser)) {
					System.out.println("Must be superuser to perform this command"); 
					continue loop;
				}
				Map<Integer, BankUser> allUsers = bankdao.viewAllUsers();
				allUsers.remove(user.getId());
				Set<Map.Entry<Integer, BankUser>> userEntries = allUsers.entrySet();
				System.out.println("Choose a user id");
				for(Map.Entry<Integer, BankUser> cur : userEntries) {
					System.out.println(cur.getValue());
				}
				int id = s.nextInt();
				if(id == user.getId()) {
					System.out.println("stop deletin urself"); continue loop;
				}
				bankdao.deleteBankUser((SuperUser)user, allUsers.get(id));
				/*the cast to SuperUser is ok because we already checked that user is an 
				 * instance of SuperUser in the preceding if statement.
				*/
			}else if(input.equalsIgnoreCase("view all users")) {
				if(!(user instanceof SuperUser)) {
					System.out.println("Must be superuser to perform this command"); 
					continue loop;
				}
				Map<Integer, BankUser> all = bankdao.viewAllUsers();
				Collection<BankUser> allBankUsers = all.values();
				for(BankUser b : allBankUsers) {
					System.out.println(b);
				}
				
			}else if(input.equalsIgnoreCase("create user")) {
				if(!(user instanceof SuperUser)) {
					System.out.println("Must be superuser to perform this command"); 
					continue loop;
				}
				System.out.println("Username?");
				String username = s.nextLine();
				System.out.println("Password");
				String pass = s.nextLine();
				System.out.println("First name?");
				String first = s.nextLine();
				System.out.println("Last name?");
				String last = s.nextLine();
				
				BankUser created = new BankUser(username, pass, first, last);
				bankdao.createBankUser(created);
				
			}else if(input.equalsIgnoreCase("update user field")) {
				if(!(user instanceof SuperUser)) {
					System.out.println("Must be superuser to perform this command"); 
					continue loop;
				}
				System.out.println("Choose a User id.");
				Map<Integer, BankUser> all = bankdao.viewAllUsers();
				Collection<BankUser> allBankUsers = all.values();
				for(BankUser b : allBankUsers) {
					System.out.println(b);
				}
				int id = Integer.parseInt(s.nextLine());
				BankUser toDelete = all.get(id);
				System.out.println("Choose a field (username, password, firstname, lastname)");
				String field = s.nextLine();
				System.out.println("Type new value");
				String newValue = s.nextLine();
				bankdao.updateUserField(toDelete, field, newValue);
			}else if(input.equalsIgnoreCase("help")) {
				System.out.println("deposit: add money to an existing account");
				System.out.println("withdraw: withdraw money from an existing account");
				System.out.println("create: create a new account");
				System.out.println("my accounts: view existing accounts, account ids, and balances");
			}
			else {
				System.out.println("Unrecognized command. Type \"help\" for a list of commands");
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
		int id = Integer.parseInt(s.nextLine());
		Account a = user.getAccountFromId(id);
		double balance = bankdao.viewBalance(user, a);
		System.out.println("Current balance is " + balance);
		System.out.print("Enter amount to ");
		if(deposit) {System.out.println("deposit");}
		else {System.out.println("withdraw");}
		double amt = Double.parseDouble(s.nextLine());
		if(!deposit) {amt = -amt;}
		bankdao.transaction(user, a, amt);
	}
	
	public void newAccount() {
		System.out.println("Account Type?");
		String accountType = s.nextLine().toUpperCase();
		System.out.println("Interest Rate?");
		double interestRate = Double.parseDouble(s.nextLine());
		System.out.println("Initial Balance?");
		double initialBalance = Double.parseDouble(s.nextLine());
		
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
		int id = Integer.parseInt(s.nextLine());
		Account a = user.getAccountFromId(id);
		if(bankdao.viewBalance(user, a)>0.0) {
			System.out.println("Account balance must be 0.0. Try again."); return;
		}
		bankdao.deleteAccount(user, a);
	}
	
	
}
