package com.revature.util;

import java.util.List;

import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;
import com.revature.domain.BankUser;

public class ApplicationSession {

	private List<BankUser> allUsers;
	private static Object lock = new Object();
	private static ApplicationSession applicationSession;
	private static BankDAO bankdao = new BankDAOImpl();
	private UserSession userSession;
	
	private ApplicationSession() {
		
	}
	
	public static ApplicationSession startApplicationSession() {
		synchronized(lock) {
			if(applicationSession == null) {
				applicationSession = new ApplicationSession();
			}
		}
		applicationSession.populateAllUsers();
		return applicationSession;
	}
	
	public void populateAllUsers() {
		
		allUsers = bankdao.viewAllUsers();
	}
	
	public static void end() {
		UserSession.logout();
		applicationSession = null;
		
	}
	
	public UserSession getUserSession() {return applicationSession.userSession;}
}
