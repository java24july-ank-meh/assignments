package com.revature.util;

import java.util.Scanner;

import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;
import com.revature.domain.BankUser;

public class UserSession {

	private static Object lock = new Object();
	private static UserSession userSession;
	private static BankDAO bankdao = new BankDAOImpl();
	private BankUser user;
	
	private UserSession(BankUser user) {
		this.user = user;
	}
	
	public static UserSession startUserSession(BankUser b) {
		
		synchronized(lock) {
			if(userSession==null) {
				userSession = new UserSession(b);
			}
		}
		return userSession;
	}
	
	public static void login() {
		
		bankdao.login(userSession.user);
	}
	
	public static void logout() {
		
		bankdao.logout(userSession.user);
	}
	
	public void sessionLoop() {
		Scanner s = new Scanner(System.in);
		if(!bankdao.isLoggedIn(user)) {
			System.out.println("Login failed. Failed to update LOGGEDIN field in BANKUSERS table");
			return;
		}
		System.out.println("You are now logged in!");
		sessionLoop:while(true) {
			System.out.println("Type next command");
			String input = s.nextLine();
			if(input.equalsIgnoreCase("logout")) {
				bankdao.logout(user);
				System.out.println("\nCome again soon!\n");
				break sessionLoop;
			}
		}
	}
	
	
}
