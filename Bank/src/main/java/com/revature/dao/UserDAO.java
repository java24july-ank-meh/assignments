package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;


public interface UserDAO
{
	public void createUser(User user);
	
	public ArrayList<User> readAllUsers();
	
	public User readUser(User user);
	
	public User updateUser(User user, User newUser);
	
	public void deleteAllUsers();

}
