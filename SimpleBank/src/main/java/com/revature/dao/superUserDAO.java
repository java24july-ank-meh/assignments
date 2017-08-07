package com.revature.dao;

import com.revature.domain.User;

public interface superUserDAO {
	public void readAllUsers();
	public boolean createUser(User u);
	public boolean deleteUser(int id);
	public boolean updateLastName(int id, String str);
	public boolean updateFirstName(int id, String str);
	public boolean updateBalance(int id, int amount);
	public boolean updateUserName(int id, String str);
	public boolean updatePassword(int id, char[] str);
}
