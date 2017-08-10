package com.revature.dao;

import java.util.List;

import com.revature.domain.User;

public interface UserDao {
	
	public List<User> getOnlyEmps();
	public List<User> getOnlyMans();
	public List<User> readAllUsers();
	
	public User readUser(int uID);
	//public User readUser(User u);
	
	public void createUser(User u);
	
	public void	 updateUser(User u);
	
	//public void deleteUser(User u);
	public void deleteUser(int uID);
	
}
