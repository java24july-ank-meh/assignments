package com.revature.dao;

import java.util.List;
import java.util.Map;

import com.revature.domain.Account;
import com.revature.domain.BankUser;
import com.revature.domain.SuperUser;

public interface BankDAO {

	public void createBankUser(BankUser b);
	public void createAccount(BankUser b, Account a);
	public void deleteAccount(BankUser b, Account a);
	public void createSuperUser(BankUser b, boolean forfeit);
	public void transaction(BankUser b, Account a, double amount);
	public double viewBalance(BankUser b, Account a);
	public void deleteBankUser(SuperUser su, BankUser b);
	public Map<Integer, BankUser> viewAllUsers();
	public List<Account> viewAllAccounts(SuperUser su);
	public List<Account> viewMyAccounts(BankUser b);
	public void login(BankUser b);
	public void logout(BankUser b);
	public BankUser getUserFromInfo(String username, String pass);
	public boolean isLoggedIn(BankUser b);
	public void populateAccountTypes();
	public void updateUserField(BankUser b, String field, String value);
}
