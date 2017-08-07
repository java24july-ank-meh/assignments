package com.revature.domain;

public class AccountUser {
	private int id;
	private String userName;
	private String password;
	private boolean superUser;
	
	public boolean isSuper() {
		return superUser;
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

	public int getId() {
		return id;
	}
	
	public void setSuper(int i) {
		if (i == 0) {
			superUser = false;
		}
		else {
			superUser = true;
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public AccountUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountUser( String userName, String password, boolean superUser) {
		super();
		this.userName = userName;
		this.password = password;
		this.superUser = superUser;
	}
	
	public AccountUser( int id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public AccountUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "AccountUser [id=" + id + ", userName=" + userName + ", password=" + password + "superUser=" + superUser + "]";
	}

}
