package com.revature.customers;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.util.ConnectionUtil;

public class CustomerDaoImpl implements CustomerDao{

	public void updateAccount(Integer id, double balance) {
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL SP_UPDATE_ACCOUNT(?,?)}";
			cs = conn.prepareCall(sql);
			
			cs.setInt(1, id);
			cs.setDouble(2, balance);
			
			cs.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(cs != null) {
				try {
					cs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ArrayList<Account> getAccounts(Integer uid) {
		PreparedStatement pstmt = null;
		ArrayList<Account> accounts = new ArrayList<>();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM ACCOUNTS WHERE USER_ID = " + uid;
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ACCOUNT_ID");
				String type = rs.getString("ACC_TYPE");
				double balance = rs.getDouble("BALANCE");
				
				Account a = new Account(type, balance, id);
				accounts.add(a);
			}
			rs.close();
			}catch(SQLException | IOException e) {
				e.printStackTrace();
			}finally {
				if (pstmt != null) {
					try { pstmt.close();} 
					catch(SQLException e) {e.printStackTrace();}}
				}
			return accounts;
	}

	public void addAccount(Integer uid, String type) {
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL SP_ADD_ACCOUNT(?,?)}";
			cs = conn.prepareCall(sql);
			
			cs.setInt(1, uid);
			cs.setString(2, type);
			
			cs.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(cs != null) {
				try {
					cs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void closeAccount(Integer id) {
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL SP_REMOVE_ACCOUNT(?)}";
			cs = conn.prepareCall(sql);
			
			cs.setInt(1, id);
			
			cs.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(cs != null) {
				try {
					cs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
