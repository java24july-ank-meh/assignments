package com.revbank.dao;

public interface UserDAO {
	
	public void readAccounts(String username);
	public Integer createNewAccount(String username);
	public void deleteAccount(int accNum);
	public void depositAccount(int accNum, double amt);
	public void withdrawAccount(int accNum, double amt);
	public Boolean accountLogin(String username, String password);
	public Boolean accountLogout(String username, String password);
	
}
