package com.revature.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.domain.BankUser;

public class ApplicationSession {

	private List<BankUser> allUsers;
	private static Object lock = new Object();
	private static ApplicationSession applicationSession;
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
		
		PreparedStatement pstmt = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BANKUSERS";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BankUser nextUser = new BankUser(rs.getString("USERNAME"), rs.getString("PASS"),
						rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
				nextUser.setId(rs.getInt("USERID"));
				allUsers.add(nextUser);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
