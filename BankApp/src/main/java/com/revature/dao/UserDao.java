package com.revature.dao;

import java.io.IOException;
import com.revature.bean.User;

public interface UserDao {
	
	public void createNewUser(String username, String password, int isAdmin);//WILL CALL SQL PROCEDURE
	public void createAccount(int U_id, String accName);// WILL CALL SQL PROCEDURE
	public void deleteUser(int U_id);//WILL CALL SQL
	public void deleteAccount(int accnt_id);//WILL CALL SQL
	public void validLogin(String username, String password);
	public void validAdminLogin(String username, String password, int x);
	public void getBalance(int accnt_id);
	public void Deposit(int U_id, double amt);	
	public User read_user(String username) throws IOException;	
}
