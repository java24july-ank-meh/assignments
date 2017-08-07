package com.banking.domain;

public class Account {
	private int accountId;
	private int userId;
	private String name;

	public int getAccountId() {
		return accountId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userId=" + userId + ", name=" + name + ", amount=" + amount + "]";
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Account(int accountId, int userId, String name, double amount) {
		super();
		this.accountId = accountId;
		this.userId = userId;
		this.name = name;
		this.amount = amount;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	private double amount;
}
