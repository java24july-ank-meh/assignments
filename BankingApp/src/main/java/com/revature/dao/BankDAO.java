package com.revature.dao;

import com.revature.domain.Account;
import com.revature.domain.BankUser;

public interface BankDAO {

	public void createBankUser(BankUser b);
	public void createAccount(BankUser b, Account a);
	public void createSuperUser(BankUser b);
	public void withdraw(BankUser b, Account a, int amount);
	public double viewBalance(BankUser b);
	public void deleteBankUser(BankUser b);
}
