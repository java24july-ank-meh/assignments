package com.revature.domain;

public class User {
	private String username;
	private String password;
	private int userid;
	private String status = "default";
	
	
	public User(String username, String password, int userid) {
		super();
		this.username = username;
		this.password = password;
		this.userid = userid;
	}
	
	public User(String status) {
		super();
		this.status = status;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getUserid() {
		return userid;
	}
	public String getStatus() {
		return status;
	}
	
}
