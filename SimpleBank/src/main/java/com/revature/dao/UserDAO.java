package com.revature.dao;

import com.revature.domain.*;

public interface UserDAO extends LoginDAO {
	public void createAccount(User u);
	public void deleteAccount(User u);
	public void deposit(User u, int amount);
	public void withdraw(User u, int amount);
}