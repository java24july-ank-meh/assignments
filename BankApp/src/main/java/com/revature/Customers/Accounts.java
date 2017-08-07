package com.revature.Customers;

public class Accounts {
	
	private int AccountNumber;
	private double AccountBalance;
	
	public int getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}
	public double getAccountBalance() {
		return AccountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		AccountBalance = accountBalance;
	}
	public Accounts (){
		super();
	}
	public Accounts(int accountNumber, double accountBalance) {
		super();
		AccountNumber = accountNumber;
		AccountBalance = accountBalance;
	}
	

}
