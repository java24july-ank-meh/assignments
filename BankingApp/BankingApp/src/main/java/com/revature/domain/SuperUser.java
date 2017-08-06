package com.revature.domain;

public class SuperUser extends BankUser{
	
	private static Object lock = new Object(); 
	private static SuperUser superUser;
	
	private SuperUser(String username, String pass, String firstname, String lastname) {
		super(username, pass, firstname, lastname);
	}
	
	public static SuperUser createSuperUser(String username, String pass, String firstname, String lastname) {
		synchronized(lock) {
			if(superUser==null) {
				superUser = new SuperUser(username, pass, firstname, lastname);
			}
		}
		return superUser;
	}
	
	public void DeleteBankUser(BankUser b) {
		
	}
	
	public void CreateBankUser(BankUser b) {
		
	}
	
	public void changeUserField(BankUser b, UserField f) {
		
	}
	
	public void forfeit() {
		
	}

}
