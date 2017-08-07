package com.revature.dao;

import java.util.List;

import com.revature.domain.Account;

public interface AccountDao {

	//create
	public void createAccount(Account a);
	//read
	public Account readAccount(int id);

	public List<Account> readAllAccount(int id);
	public List<Account> readAllAccount();

	//delete
	public boolean deleteAccount(Account a);

	//--------------------------------

	//update 
	public void depositMoney(int accid, double amt);
	public void depositMoney(Account a, double amt);

	public boolean withdrawMoney(int accid, double amt);
	public boolean withdrawMoney(Account a, double amt);


}
