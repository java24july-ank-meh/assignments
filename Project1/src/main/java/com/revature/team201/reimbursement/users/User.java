package com.revature.team201.reimbursement.users;

import java.io.IOException;
import java.sql.SQLException;

import com.revature.team201.reimbursement.db.UserDAOImpl;
import com.revature.team201.reimbursement.util.ConnectionUtil;

public class User {
	// Instance variables
	private Integer userId;
	private String username;
	private String firstName;
	private String lastName;
	private String eMail;
	private Role userRole;
	

	public User(Integer uId, String uname, String firstname, String lastname, String email, Role role) {
		userId = uId;
		username = uname;
		firstName = firstname;
		lastName = lastname;
		eMail = email;
		userRole = role;
	}

	
	/*
	 * Getters and setters
	 */
	public Integer getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public Role getUserRole() {
		return userRole;
	}
	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
}
