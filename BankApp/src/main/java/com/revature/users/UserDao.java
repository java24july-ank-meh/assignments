package com.revature.users;

import com.revature.customers.Customer;

public interface UserDao {
	public User getUser(String username,String pass);
	public void addUser(String username, String pass);
	public Customer checkUser(String username);
}
