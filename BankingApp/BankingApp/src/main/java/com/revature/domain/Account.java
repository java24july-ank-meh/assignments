package com.revature.domain;

public class Account {

	private BankUser bankUser;
	private double balance;
	private AccountType accountType;
	private double interestRate;
	
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

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public Account(BankUser bankUser, double balance, AccountType accountType, double interestRate) {
		this.bankUser = bankUser;
		this.accountType = accountType;
		this.interestRate = interestRate;
		this.balance = 0.0;
	}
}
