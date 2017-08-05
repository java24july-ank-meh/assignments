package com.revature.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

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
			if(userSession!=null) {
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
	
	
}
