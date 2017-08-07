package com.revature.users;

public abstract class User {
	// EVERY USER HAS A USERNAME
	private String username;
	private Integer userId;
	public UserDaoImpl uDao = new UserDaoImpl();
	// CONSTRUCTORS FOR USER TYPE
	protected User (){}
	protected User(String name, Integer id) {
		username = name;
		userId = id;
	}
	// USER FUNCTIONS
	public abstract void menu();
	public abstract void execute(char c);
	// USERNAME GETTER/SETTER METHODS
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getUserId() {
		return userId;
	}
}
