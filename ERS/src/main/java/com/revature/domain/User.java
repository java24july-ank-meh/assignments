package com.revature.domain;

import java.util.ArrayList;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String role;
	
	private ArrayList<Reimbursement> pendingReimbursements;
	private ArrayList<Reimbursement> resolvedReimbursements;
	
	
	public ArrayList<Reimbursement> getPendingReimbursements() {
		return pendingReimbursements;
	}
	public void addPendingReimbursements(Reimbursement pendingReimbursement) {
		this.pendingReimbursements.add(pendingReimbursement);
	}
	public ArrayList<Reimbursement> getResolvedReimbursements() {
		return resolvedReimbursements;
	}
	public void addResolvedReimbursements(Reimbursement resolvedReimbursement) {
		this.resolvedReimbursements.add(resolvedReimbursement);
	}
	public User(int id) {
		super();
		this.id = id;
	}
	public User(int id, String username, String password, String firstname, String lastname, String email,
			String role) {
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
	public String getRole() {
		return role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", role=" + role + "]";
	}
	
	
}
