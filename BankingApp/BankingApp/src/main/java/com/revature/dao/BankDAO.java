package com.revature.dao;

import java.util.List;

import com.revature.domain.Account;
import com.revature.domain.BankUser;
import com.revature.domain.SuperUser;

public interface BankDAO {

	public void createBankUser(BankUser b);
	public void createAccount(BankUser b, Account a);
	public void createSuperUser(BankUser b);
	public void withdraw(BankUser b, Account a, int amount);
	public double viewBalance(BankUser b);
	public void deleteBankUser(SuperUser su);
	public List<BankUser> viewAllUsers();
	public List<Account> viewAllAccounts(SuperUser su);
	public void login(BankUser b);
	public void logout(BankUser b);
	public BankUser getUserFromInfo(String username, String pass);
	public boolean isLoggedIn(BankUser b);
}
