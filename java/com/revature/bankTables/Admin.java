package com.revature.bankTables;

public class Admin extends User {
	
	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;
	private int priv = 1;
	@Override
	public String toString() {
		return "SpecialUser [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", passWord=" + passWord + "]";
	}

	public Admin(String firstName, String lastName, String userName, String passWord) {
		super(firstName, lastName, userName, passWord);
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.passWord = passWord;
	}

	public Admin() {
		
	}
	public int getPriv() {
		return priv;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
