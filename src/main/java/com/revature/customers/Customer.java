package com.revature.customers;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.users.User;

@SuppressWarnings("resource")
public class Customer extends User{
	private ArrayList<Account> accounts;
	public CustomerDao cDao = new CustomerDaoImpl();
	public Customer() {}
	public Customer(String username, Integer id){
		super(username, id);
		accounts = cDao.getAccounts(id);
	}
	// GETTER FOR ACCOUNTS
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	// ADD AN ACCOUNT
	public void initializeAccount() {
		this.accounts = cDao.getAccounts(this.getUserId());
	}
	@Override
	public void menu() {
		System.out.println("\nPlease select a menu option: ");
		System.out.println("(V)iew accounts.");
		System.out.println("(A)dd an account.");
		System.out.println("(C)lose an account.");
		System.out.println("(D)eposit into an account.");
		System.out.println("(W)ithdraw from an account.");
		System.out.println("(L)ogout.");
	}
	@Override
	public void execute(char c) {
		switch(c) {
		case 'V': 	view();
				  	break;
		case 'A':	add();
				  	break;
		case 'C': 	close();
					break;
		case 'D': 	deposit();
					break;
		case 'W': 	withdraw();
					break;
		default: 	break;
		}
	}
	
	public void view() {
		System.out.println("#   Type         Balance");
		char[] arr = new char[25];
		for(int x = 0; x < accounts.size(); x++) {
			for(int y = 0; y < arr.length; y++)
				arr[y] = ' ';
			String str = Integer.toString(x);
			for(int y = 0; y < str.length(); y++) {
				arr[y] = str.charAt(y);
			}
			str = accounts.get(x).getAccountType();
			if(str.length()>13)
				str = str.substring(0, 10)+"...";
			for(int y = 0; y < str.length(); y++) {
				arr[y+4] = str.charAt(y);
			}
			str = Double.toString(accounts.get(x).getBalance());
			arr[17] = '$';
			for(int y = 0; y < str.length(); y++) {
				arr[y+18] = str.charAt(y);
			}
			System.out.println(arr);
		}
		
	}
	public void add() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What type of account would you like to create? (ex. checking, savings): ");
		String s = sc.next();
		if(s.equals("cancel"))
			return;
		cDao.addAccount(this.getUserId(), s);
		initializeAccount();
		System.out.println("New "+ s + " account created.");
	}
	public void close() {
		view();
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		Integer acc;
		do {
			System.out.print("Which account number would you like to close?: ");
			String s = sc.next();
			if(s.equals("cancel"))
				return;
			acc = Integer.parseInt(s);
			if(acc < accounts.size() && acc >= 0)
				flag = false;
			else {
				System.out.println("Not a valid account number. Please try again.");
			}
		}while(flag);
		if(accounts.get(acc).getBalance() == 0) {
			cDao.closeAccount(accounts.get(acc).getAccountId());
			accounts.remove((int)acc);
			System.out.println("Account successfully closed");
		}
		else {
			System.out.println("Unable to close account. Account still has funds remaining.");
		}
			
	}
	public void deposit() {
		view();
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		Integer acc;
		do {
			System.out.print("Which account number would you like to deposit to?: ");
			String s = sc.next();
			if(s.equals("cancel"))
				return;
			acc = Integer.parseInt(s);
			if(acc < accounts.size() && acc >= 0)
				flag = false;
			else {
				System.out.println("Not a valid account number. Please try again.");
			}
		}while(flag);
		System.out.print("Enter amount to deposit: ");
		String s = sc.next();
		if(s.charAt(0) == '$')
			s = s.substring(1, s.length()-1);
		Double amount = Double.parseDouble(s);
		accounts.get(acc).deposit(amount);
		cDao.updateAccount(accounts.get(acc).getAccountId(), accounts.get(acc).getBalance());
		System.out.println(amount + " deposited. New balance is " + accounts.get(acc).getBalance() + ".");
	}
	public void withdraw() {
		view();
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		Integer acc;
		do {
			System.out.print("Which account number would you like to withdraw from?: ");
			String s = sc.next();
			if(s.equals("cancel"))
				return;
			acc = Integer.parseInt(s);
			if(acc < accounts.size() && acc >= 0)
				flag = false;
			else {
				System.out.println("Not a valid account number. Please try again.");
			}
		}while(flag);
		System.out.print("Enter amount to withdraw: ");
		String s = sc.next();
		if(s.charAt(0) == '$')
			s = s.substring(1, s.length()-1);
		Double amount = Double.parseDouble(s);
		if(accounts.get(acc).withdraw(amount)) {
			cDao.updateAccount(accounts.get(acc).getAccountId(), accounts.get(acc).getBalance());
			System.out.println("$" + amount + " withdrawn. New balance is $" + accounts.get(acc).getBalance() + ".");
		}
		else
			System.out.println("Insufficient funds. Account balance is $" + accounts.get(acc).getBalance());
	}
	
}
