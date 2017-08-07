package com.revature.alvarado.alejandro.bank;

public class BankUser {
	
	private Integer userId;
	private String name;
	private Boolean isAdmin;
	private String password;

	public BankUser(Integer userId, String name, Boolean isAdmin, String password) {
		this.userId = userId;
		this.name = name;
		this.isAdmin = isAdmin;
		this.password = password;
	}
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Integer getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}
	
}
