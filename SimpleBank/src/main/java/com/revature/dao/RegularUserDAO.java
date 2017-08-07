package com.revature.dao;

import com.revature.domain.User;

public interface RegularUserDAO {
	public boolean withdraw(int id, int amount);
	public boolean deposit(int id, int acct, int amount);
	public boolean getTransactionHistory(int account_number);
}
