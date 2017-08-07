package com.revature.domain;

import java.util.List;

import com.revature.dao.*;

public class User {

	/*User member variables 
	 * all are in user table, 
	 * except for accounts
	 * 		(because it will collaborate with matching userID in account table)*/
	private int userID;
	private String username;
	private String password;
	private double totalBalance;

	private String fName;
	private String mName;
	private String lName;

	private String address;
	private String city;
	private String state;
	private String postalCode;
	
	private List<Account> accounts;
	
	//---------------------------------------------------
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int i, String u, String p) {
		// TODO Auto-generated constructor stub
		userID = i;
		username = u;
		password = p;
	}
	public User(String u, String p, String f, String l) {
		username = u;
		password = p;
		fName = f;
		lName = l;
	}
	public User(int i, String u, String p, String f, String m,String l) {
		// TODO Auto-generated constructor stub
		userID = i;
		username = u;
		password = p;
		fName= f;
		mName = m;
		lName = l;
	}
	public User(int i,double b, String f, String m,String l, String a, String c, String s, String pc) {
		// TODO Auto-generated constructor stub
		userID = i;
		totalBalance = b;
		fName= f;
		mName = m;
		lName = l;
		address = a;
		city = c;
		state = s;
		postalCode = pc;
	}
	
	public void updateSelf() {
		UserDao uD = new UserDaoImpl();
		User uNew = uD.readUser(userID);
		
		username = uNew.getUsername();
		password = uNew.getPassword();
		totalBalance = uNew.getTotalBalance();
		fName= uNew.getfName();
		mName = uNew.getmName();
		lName = uNew.getlName();
		address = uNew.getAddress();
		city = uNew.getCity();
		state = uNew.getState();
		postalCode = uNew.getPostalCode();
		
	}
	
	//-----------------------------------------------------
	
	@Override
	public String toString() {
		return "User [userID=" + userID + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", address="
				+ address + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode + ", accounts="
				+ accounts + "]";
	}	
	//-----------------------------------------------------

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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
		
	public double getTotalBalance() {
		return totalBalance;
	}
	
	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}
	
	public void setTotalBalanceFromDB() {
		UserDao uD = new UserDaoImpl();
		this.totalBalance = uD.userTotalBalanceSP(userID);
	}
	
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public List<Account> setAccountsFromDB() {
		AccountDao adI = new AccountDaoImpl();
		accounts = adI.readAllAccount(userID);
		return accounts;
	}
	
}
