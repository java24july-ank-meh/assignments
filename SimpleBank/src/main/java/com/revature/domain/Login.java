package com.revature.domain;

import java.util.Arrays;

public class Login {
	private String userName;
	private char[] password;

	public Login(String userName, char[] password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [userName=" + userName+"]";
	}

}
