package com.revature.dao;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.User;

public interface AccountDAO
{
	public void createAccount( Account account, int userId);
	
	public ArrayList<Account> readAllAccounts(int userId);
	
	public Account deposit(Account account, double amount);
	
	public Account withdraw(Account account, double amount);

	public void deleteAccount(Account account);

}
