package com.revature.dao;

import java.sql.SQLException;

import com.revature.domain.User;
import com.revature.exception.OverdraftException;
import com.revature.exception.SQLStatementFailedException;
import com.revature.exception.UsernameTakenException;

public interface BankingDAO {
	//create
	public void createAccount(String accountName, User u);
	public void registerUser(String username, String password);
	//read
	public void viewAccounts(User u);
	public void viewUsers();
	public User checkLogin(String username, String password);
	public void checkUsernameAvail(String username) throws UsernameTakenException;
	//update
	public double updateAccount(String accname, double amount, User u) throws OverdraftException, SQLException;
	public void updateUser(String user, String newUser, String newPass) throws SQLStatementFailedException;
	//delete
	public void deleteAccount(String accname, User u) throws SQLStatementFailedException;
	public void deleteUser(String username) throws SQLStatementFailedException;

	
}
