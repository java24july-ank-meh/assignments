package com.revature.domain;

public class User {

	private int uID;
	private String userName;
	private String password;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String email;
	private int roleID;		//or UserRoles role
	private boolean manager;

	
	public User(int uID, String userName, String password, String firstName, String middleInitial, String lastName,
			String email, int roleID) {
		super();
		this.uID = uID;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.email = email;
		this.roleID = roleID;
		
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	//----------------------------------------------------------
	
	public int getuID() {
		return uID;
	}

	public void setuID(int uID) {
		this.uID = uID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
		if(roleID==1) {
			this.manager = true;
		}else {
			this.manager = false;
		}
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitital) {
		this.middleInitial = middleInitital;
	}

}
