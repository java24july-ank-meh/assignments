package com.revature.domain;

public class Transaction {
	private int transaction_ID;
	private int account_number;
	private String type;
	private int amount;

	@Override
	public String toString() {
		return "Transaction [transaction_ID=" + transaction_ID + ", account_number=" + account_number + ", type=" + type
				+ ", amount=" + amount + "]";
	}

	public int getTransaction_ID() {
		return transaction_ID;
	}

	public void setTransaction_ID(int transaction_ID) {
		this.transaction_ID = transaction_ID;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Transaction(int transaction_ID, int account_number, String type, int amount) {
		super();
		this.transaction_ID = transaction_ID;
		this.account_number = account_number;
		this.type = type;
		this.amount = amount;
	}
}
