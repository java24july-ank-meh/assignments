package com.banking.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.banking.domain.Account;
import com.banking.domain.User;
import com.banking.exception.OverDraftException;

public interface BankDao {
	public void deleteAccount(int id, String name) throws IOException;

	public double depositAmount(int id, String name, double i) throws IOException;

	public double withdrawAmount(int id, String name, double i) throws IOException, OverDraftException;

	public List<Account> viewAllAccounts(User u) throws IOException;

	public void createAccount(Account a) throws IOException;

	public List<User> viewUsers() throws IOException;

	public void createUser(String name, String password, int superuser) throws IOException;

	public void deleteUser(int id) throws IOException;

	public void updateUser(int id, String username, String password, int superuser) throws IOException;
	
	public User login(String username, String password) throws IOException;

	public double accountTotal(int user_id, String name) throws SQLException, IOException;
}
