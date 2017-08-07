package com.revature.bankTables;

public class Transaction {
	private double deposit;
	private double withdraw;
	private int userId;
	
	public Transaction() {
		
	}
	public Transaction(double deposit, double withdraw, int userId) {
		super();
		this.deposit = deposit;
		this.withdraw = withdraw;
		this.userId = userId;
	}

	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public double getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}