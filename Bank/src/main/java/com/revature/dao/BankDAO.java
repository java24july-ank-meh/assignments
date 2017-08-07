package com.revature.dao;

import java.util.List;

import com.revature.domain.AccountUser;


public interface BankDAO {
	
	public void createAccountUser(AccountUser u);
	public void deleteAccountUser(int id);
	public void initAccountUser(AccountUser u);
	
	public void readAccountUser(int id);
	public List<AccountUser> readAllAccountUser();
	
	public void withdraw(int bid, int amt);
	public void deposit(int bid, int amt);
	public void viewBaccounts(AccountUser u);
	public void newBaccount(int amt, int id);
	public void deleteBaccount(int id);
	public void deleteAllAcountUsers();
	
}
