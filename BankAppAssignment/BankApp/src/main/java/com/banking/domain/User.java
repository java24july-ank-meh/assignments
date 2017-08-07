package com.banking.domain;

public class User {
	private int userId;
	private String username;
	private String password;
	private int superuser;

	public int getUserId() {
		return userId;
	}

	public int isSuperuser() {
		return superuser;
	}

	public void setSuperuser(int superuser) {
		this.superuser = superuser;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(int userId, String username, String password, int superuser) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.superuser = superuser;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", superuser="
				+ superuser + "]";
	}

	public User() {
		super();
		superuser = -1;
		// TODO Auto-generated constructor stub
	}
}
