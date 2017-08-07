package com.revature.dao;

import com.revature.bankTables.*;

public interface BankDao {
	public void createUser(User a);
	public String readUser(String userName, String passin);
	public void updateUser(User a, String firstName, String lastName, String passWord, String userName);
	public void deleteUser(User a);
	
	public void createAccount(Account a);
	public void readAccount(int id);
	public void updateAccount(Account a);
	public void deleteAccount(Account a);
	
	public void createTransaction(Transaction t);
	public void readTransaction(int id);
	public void updateTransaction(Transaction t);
	public void deleteTransaction(Transaction t);
}
