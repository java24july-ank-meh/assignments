package com.revature.superuser;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.customers.Customer;
import com.revature.util.ConnectionUtil;

public class SuperUserDaoImpl implements SuperUserDao{

	public void viewUsers(){
		PreparedStatement pstmt = null;
		List<Customer> customers = new ArrayList<>();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM CUSTOMERS";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("USER_ID");
				String username = rs.getString("USER_NAME");
	
				Customer c = new Customer(username, id);
				customers.add(c);
			}
			rs.close();
			}catch(SQLException | IOException e) {
				e.printStackTrace();
			}finally {
				if (pstmt != null) {
					try { pstmt.close();} 
					catch(SQLException e) {e.printStackTrace();}}
				}
			for(Customer c : customers) {
				System.out.println(c.getUserId() + " " + c.getUsername());
			}
	}

	public void removeUser(Integer id) {
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL SP_REMOVE_USER(?)}";
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

	public void updateUser(Integer id, String username, String password) {
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL SP_UPDATE_USER(?,?,?)}";
			cs = conn.prepareCall(sql);
			
			cs.setInt(1, id);
			cs.setString(2, username);
			cs.setString(3, password);
			
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
