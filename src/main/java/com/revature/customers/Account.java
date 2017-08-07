package com.revature.customers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {
	private String accountType;
	private Double balance;
	private Integer accountId;
	
	public Account(String type, Integer id){
		accountType = type;
		setAccountId(id);
		balance = 0.00;
	}
	public Account(String type, double bal, Integer id){
		accountType = type;
		setAccountId(id);
		balance = round(bal);
	}
	String getAccountType() {
		return accountType;
	}
	double getBalance() {
		return balance;
	}
	void deposit(double balance) {
		this.balance += round(balance);
	}
	boolean withdraw(double balance) {
		if(this.balance - balance >= 0) {
			this.balance = round(this.balance-balance);
			return true;
		}
		return false;
	}
	public static double round(double value) {
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}
