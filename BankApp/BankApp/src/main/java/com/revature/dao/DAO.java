package com.revature.dao;
import java.util.*;

import com.revature.domain.*;
import com.revature.exceptions.*;

public interface DAO {
	//existingUserException, UserNotFoundException, incorrectPasswordException
	public boolean createUser(User user) //create new user
			throws ExistingUserException;
	public void updateUser(User user); //change username/passoword
	public boolean deleteUser(User user)
			throws UserNotFoundException; //delete user
	public void readUser(User user); //get all user accounts
	public boolean verifyUser(User user) //verify username/password is correct
			throws UserNotFoundException, IncorrectPasswordException; 
	public List<User> readAllUsers();
	
	//overdraftException, negativeMoneyException, existingAccountException, accountNotFoundException
	public void createAccount(Account account); //create an account
	public void UpdateAccount(Account account); //change account balance
	public void deleteAccount(Account account)  //delete account
			throws AccountNotFoundException, AccountNotEmptyException;
	public void readAccount(Account account); //read account balance
	public List<Account> readAllAccounts(User user); //get all accounts of a user
	public void withdraw(Account account,double amount) //withdraw from account
			throws OverdraftException;
	public void deposit(Account account,double amount); //deposit to account
}
