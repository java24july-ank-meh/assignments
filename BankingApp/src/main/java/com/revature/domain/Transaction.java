package com.revature.domain;

public class Transaction {
	
	/*main Transaction member variables*/
	//id of transaction from table
	private int transactionID;//might do string instead
	
	private String type;//withdraw or deposit, but probably not view, but maybe
	private int accountID;
	private int userID;
	private double amountOfTransaction;
	private String time;

	/*maybe
	private User u;
		//or
	private Account a;
		//or
	private int idOfUser;
	*/
	//---------------------------------------
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(int i, int a, String t, double amt) {
		transactionID = i;
		accountID = a;
		type = t;
		amountOfTransaction = amt;
	}
	
	//--------------------------------------
	
	public int getTransactionId() {
		return transactionID;
	}

	public void setTransactionId(int transactionId) {
		this.transactionID = transactionId;
	}
	
	public double getAmountOfTransaction() {
		return amountOfTransaction;
	}

	public void setAmountOfTransaction(double amountOfTransaction) {
		this.amountOfTransaction = amountOfTransaction;
	}
	
	public int getIdOfAccount() {
		return accountID;
	}

	public void setIdOfAccount(int idOfAccount) {
		this.accountID = idOfAccount;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	//--------------------------------------------------
	//maybe
	/*
	public Account getA() {
		return a;
	}

	public void setA(Account a) {
		this.a = a;
	}
	
	public int getIdOfUser() {
		return idOfUser;
	}

	public void setIdOfUser(int idOfUser) {
		this.idOfUser = idOfUser;
	}
	
	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
	*/
}
