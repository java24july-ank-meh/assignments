package com.revature.domain;

public class Accounts {

	private int userID;
	private int accountNum;
	private int balance;
	
	
	@Override
	public String toString() {
		return "Accounts [userID=" + userID + ", accountNum=" + accountNum + ", balance=" + balance + "]";
	}
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Accounts(int accountNum, int balance) {
		super();
		this.userID = 0;
		this.accountNum = accountNum;
		this.balance = balance;
	}
	
	
}
