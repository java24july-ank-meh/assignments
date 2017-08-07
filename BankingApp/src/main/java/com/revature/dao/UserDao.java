package com.revature.dao;
import java.util.List;

import com.revature.domain.User;

public interface UserDao {

	public void createUser(User u);
	
	public User readUser(int id);
	public List<User> readAllUsers();
		
	public User updateUserL(User u);
	public User updateUserI(User u);
	public User compileUpdatedValues(User u, User u1);
	
	public void deleteUser(int id);
	public boolean deleteUser(User u);
	
	//-------------------
	public double userTotalBalance(int id);
	public double userTotalBalance(User u);
	public double userTotalBalanceSP(int id);
	public double userTotalBalanceSP(User u);	
}
