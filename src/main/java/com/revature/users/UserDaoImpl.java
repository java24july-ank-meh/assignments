package com.revature.users;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.customers.Customer;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao{
	@Override
	public User getUser(String username, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer c = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM CUSTOMERS WHERE USER_NAME = ? AND U_PASSWORD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			Integer userId = null;
			if(rs.next()) {
				userId = rs.getInt("USER_ID");
				System.out.println("UserID: " + userId);
				c = new Customer(username, userId);
			}
			rs.close();
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		return c;
	}
	@Override
	public void addUser(String username, String pass) {
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL SP_ADD_USER(?,?)}";
			cs = conn.prepareCall(sql);
			
			cs.setString(1, username);
			cs.setString(2, pass);
			
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
	public Customer checkUser(String username) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer c = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM CUSTOMERS WHERE USER_NAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			Integer userId = null;
			if(rs.next()) {
				userId = rs.getInt("USER_ID");
				c = new Customer(username, userId);
			}
			rs.close();
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		return c;
	}
}
