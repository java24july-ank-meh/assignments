package com.revature.domain;

public class Account {

	private int id;
	private BankUser bankUser;
	private double balance;
	private String accountType;
	private double interestRate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public BankUser getBankUser() {
		return bankUser;
	}

	public void setBankUser(BankUser bankUser) {
		this.bankUser = bankUser;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public Account(BankUser bankUser, double balance, String accountType, double interestRate) {
		this.bankUser = bankUser;
		this.accountType = accountType;
		this.interestRate = interestRate;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return accountType + " account " + id + " held by user " 
				+ bankUser.getUsername(); 
	}
}
