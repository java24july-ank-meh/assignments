package com.ers.main;

public class ERSUser {
	
	private int uid;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private int userRole;
	
	// Constructors
	public ERSUser() {
		super();
	}
	public ERSUser(int uid, String username, String password, String firstname, String lastname, String email,
			int userRole) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.userRole = userRole;
	}
	
	
	
	// Getters and Setters
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	
	
	
	
}
