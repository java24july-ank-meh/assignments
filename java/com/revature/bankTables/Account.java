package com.revature.bankTables;

public class Account {
	//private int accountId; //this will be created with a trigger in the DB
	private double balance;
	private int userId;
	private int accountType;
	
	public Account( double balance, int userId) {
		super();
		//this.accountId = accountId;
		this.balance = balance;
		this.userId = userId;
		this.accountType = accountType;
	}
	public Account() {
		
	}
	@Override
	public String toString() {
		return "Account [  balance=" + balance + ", accountType" + accountType+ "]";
	}
//	public int getAccountId() {
//		return accountId;
//	}
//	public void setAccountId(int accountId) {
//		this.accountId = accountId;
//	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getAccountType() {
		return accountType;
	}
}
