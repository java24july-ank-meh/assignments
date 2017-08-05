package com.revature.domain;

import java.util.List;

public class BankUser {

	private int id;
	private String username;
	private String pass;
	private String firstname;
	private String lastname;
	private List<Account> accounts;
	
	public BankUser(String username, String pass, String firstname, String lastname) {
		this.username = username;
		this.pass = pass;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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
	
	public List<Account> getAccounts(){
		return this.accounts;
	}
	
	public void addAccount(Account a) {
		this.accounts.add(a);
	}
	
	public void forfeit() {
		
	}
	
}
