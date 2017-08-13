package com.revature.domain;

import java.util.ArrayList;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private int role;
	
	private Reimbursement[] pendingReimbs;
	private Reimbursement[] resolvedReimbs;
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public void setPendingReimbursements(Reimbursement[] pendingReimbursements) {
		this.pendingReimbs = pendingReimbursements;
	}
	public void setResolvedReimbursements(Reimbursement[] resolvedReimbursements) {
		this.resolvedReimbs = resolvedReimbursements;
	}
	public Reimbursement[] getPendingReimbursements() {
		return pendingReimbs;
	}
	public Reimbursement[] getResolvedReimbursements() {
		return resolvedReimbs;
	}
	public User(int id) {
		super();
		this.id = id;
	}
	public User(int id, String username, String password, String firstname, String lastname, String email,
			int role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getEmail() {
		return email;
	}
	public int getRole() {
		return role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", role=" + role + "]";
	}
	
	
}
