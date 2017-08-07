package com.revature.superuser;

public interface SuperUserDao {
	public void viewUsers();
	public void removeUser(Integer id);
	public void updateUser(Integer user, String username, String pass);
}
