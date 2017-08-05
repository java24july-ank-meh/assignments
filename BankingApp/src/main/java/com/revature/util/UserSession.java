package com.revature.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.domain.BankUser;

public class UserSession {

	private static Object lock = new Object();
	private static UserSession userSession;
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
		
		CallableStatement cs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL LOGIN(?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, userSession.user.getId());
			cs.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void logout() {
		
		CallableStatement cs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL LOGOUT(?)}";
			cs.setInt(1, userSession.user.getId());
			cs.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
