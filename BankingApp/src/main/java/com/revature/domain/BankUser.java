package com.revature.domain;

import java.util.List;

public class BankUser {

	private String fName;
	private String lName;
	private String phoneNum;
	private String uname;
	private String pw;
	private String email;
	private int userID;
	private List<Accounts> accountList;
	
	
	@Override
	public String toString() {
		return "BankUser [fName=" + fName + ", lName=" + lName + ", phoneNum=" + phoneNum + ", uname=" + uname
				+ ", email=" + email + "]";
	}
	public BankUser() {
		super();
	}
	public List<Accounts> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Accounts> accountList) {
		this.accountList = accountList;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public BankUser(String fName, String lName, String phoneNum, String uname, String pw, String em) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.phoneNum = phoneNum;
		this.uname = uname;
		this.pw = pw;
		this.email = em;
		this.userID = 0;
	}
	
	
	
}
