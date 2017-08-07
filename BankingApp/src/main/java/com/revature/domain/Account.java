package com.revature.domain;

import java.util.*;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.TransactionDao;
import com.revature.dao.TransactionDaoImpl;

public class Account {

	/*User member variables 
	 * all are in user table, 
	 * except for accounts
	 * 		(because it will collaborate with matching userID in account table)*/
	private int accountID;
	private int userID;
	private String accountName;
	private User u;
	private double balance;
	private List<Transaction> transactions;
	//could go into account type with enum or string, which would affect amount as time goes by(years/months/weeks) if it wasnt a console banking app, of course the change/flux/influx would happen on server side

	//---------------------------------------------------
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(int i,int d, String a, double b) {
		// TODO Auto-generated constructor stub
		accountID = i;
		userID = d;
		accountName = a;
		balance = b;
	}

	//-----------------------------------------------------

	@Override
	public String toString() {
		if(u != null) {
			return "User [accountID=" + accountID + "/ accountName=" + accountName + " of user-> userID=" + userID + ", fName=" + u.getfName() + ", mName=" + u.getmName() + ", lName=" + u.getlName() + " >- has a remaining balance of " + balance + "]";
		}else {
			return "User [accountID=" + accountID + "/ accountName=" + accountName +" of user with userID=" + userID +" with remaining balance of " + balance + "]";
		}
	}	

	//-----------------------------------------------------
	
	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public List<Transaction> setTransactionsFromDB() {
		TransactionDao tdI = new TransactionDaoImpl();
		transactions = tdI.readAllTransaction("account",accountID);
		return transactions;
	}

}
