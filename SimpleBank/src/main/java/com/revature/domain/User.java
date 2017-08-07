package com.revature.domain;

public class User {
	private Login logininfo;
	
	private int accountID;
	private int accountNumber;
	private String lastName;
	private String firstName;
	private int balance;

	public User(Login logininfo, int accountID, int accountNumber, String lastName, String firstName,
			int balance) {
		super();
		this.logininfo = logininfo;
		this.accountID = accountID;
		this.accountNumber = accountNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.balance = balance;
	}
	
	public User(int accountID, int accountNumber, String lastName, String firstName,
			int balance) {
		super();
		this.accountID = accountID;
		this.accountNumber = accountNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.balance = balance;
	}
	
	public User(Login logininfo, int accountNumber, String lastName, String firstName, int balance) {
		super();
		this.logininfo=logininfo;
		this.accountNumber = accountNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User [Account ID=" + accountID
				+ ", Accoount Number=" + accountNumber + ", Last Name=" + lastName + ", First Name=" + firstName + ", balance="
				+ balance + "]";
	}

	public Login getLogininfo() {
		return logininfo;
	}

	public void setLogininfo(Login logininfo) {
		this.logininfo = logininfo;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
