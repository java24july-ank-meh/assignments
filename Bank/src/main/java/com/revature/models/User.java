package com.revature.models;

public class User
{
	private int userId;
	private String username;
	private String password;

	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	public User(int userId, String username, String password)
	{
		this.username = username;
		this.password = password;
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}

	public int getUserID()
	{
		return this.userId;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		return "User [userId " + userId + ", username=" + username + ", password=" + password + "]";
	}

}
