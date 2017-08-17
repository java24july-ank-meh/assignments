package com.revature.bank.DAO;

import java.sql.*;
import java.util.List;
import com.revature.bank.Connection.*;
import com.revature.bank.Process.Person;

public class DAOFunctImp implements DAOFunct {
	
	@Override
	public void createPerson(Person p) {//works
		try(Connection conn = ConnectionUtil.getConnection()){
			String fname = p.getUser_fname();
			String lname = p.getUser_lname();
			String email = p.getUser_email();
			String address = p.getUser_address();
			String city = p.getUser_city();
			String state = p.getUser_state();
			int phone = p.getUser_phone();
			
			String sql = "INSERT INTO user_table (user_fname, user_lname, user_email, user_address, user_city, user_state, user_phone)"
					+ " VALUES('" + fname +"', '" + lname +"', '" + email +"', '" + address +"', '" + city +"', '" + state + "', " + phone + ")";
			
			Statement stmt = conn.createStatement();
			int nRowsAffected = stmt.executeUpdate(sql);
			
			System.out.println("Rows affected: " + nRowsAffected);
			
			/*when a new user is created, automatically the account is created with balance of 0.00*/
			String sql2 = "INSERT INTO user_account(balance) VALUES(0.00)";
			stmt.executeUpdate(sql2);
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
	}

	@Override
	public void readPerson(int id) {//works
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM user_table WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String fname = rs.getString("user_fname");
				String lname = rs.getString("user_lname");
				String email = rs.getString("user_email");
				String address = rs.getString("user_address");
				String city = rs.getString("user_city");
				String state = rs.getString("user_state");
				int phone = rs.getInt("user_phone");
				
				System.out.println("User Info:");
				System.out.println("User Name: " + fname + " " + lname);
				System.out.println("User email: " + email);
				System.out.println("User Address: " + address + ", " + city + ", " + state );
				System.out.println("User Phone: " + phone);
			}
			
		}catch(SQLException se) {
			if(pstmt != null) {
				System.out.println("problem reading the database.");
				try {
					pstmt.close();
				}catch(SQLException sqle) {
					sqle.printStackTrace();
				}
				
			}
			
			if(rs != null) {
				System.out.println("problem reading the database.");
				try {
					rs.close();
				}catch(SQLException sqlex) {
					sqlex.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Person> readAll() {//not implemented 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePerson(Person p, int id) {// works 
		
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String fname = p.getUser_fname();
			String lname = p.getUser_lname();
			String email = p.getUser_email();
			String address = p.getUser_address();
			String city = p.getUser_city();
			String state = p.getUser_state();
			int phone = p.getUser_phone();
			
			String sql = "UPDATE user_table SET user_fname = ?, user_lname = ?, user_email = ?, user_address = ?, user_city = ?, user_state = ?, user_phone = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, email);
			pstmt.setString(4, address);
			pstmt.setString(5, city);
			pstmt.setString(6, state);
			pstmt.setInt(7, phone);
			pstmt.setInt(8, id);
			
			
			pstmt.executeQuery();
			System.out.println("Row updated!");
			
		}catch(SQLException se) {
			se.printStackTrace();
			if(pstmt != null) {
				System.out.println("problem updating the database.");
				try {
					pstmt.close();
				}catch(SQLException sqle) {
					sqle.printStackTrace();
				}
				
			}
		}

	}

	@Override
	public void deletePerson(int id) {//works
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM user_table WHERE user_id = ?";
			String sql2 = "DELETE FROM user_account WHERE acct_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt.setInt(1, id);
			pstmt2.setInt(1, id);
			
			pstmt.executeQuery();
			pstmt2.executeQuery();
			System.out.println("User deleted:Account Deleted");
			
		}catch(SQLException se) {
			se.printStackTrace();
			if(pstmt != null) {
				System.out.println("problem updating the database.");
				try {
					pstmt.close();
				}catch(SQLException sqle) {
					sqle.printStackTrace();
				}
				
			}
			if(pstmt2 != null) {
				System.out.println("problem updating the database.");
				try {
					pstmt2.close();
				}catch(SQLException sqle) {
					sqle.printStackTrace();
				}
				
			}
		}

	}

	@Override
	public void readBal(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT balance FROM user_account WHERE user_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				double balance = rs.getDouble("balance");
				
				System.out.println(balance);
				System.out.println("weird");
			}
			
			
		}catch(SQLException se) {
			if(pstmt != null) {
				System.out.println("problem reading the database.");
				try {
					pstmt.close();
				}catch(SQLException sqle) {
					sqle.printStackTrace();
				}
				
			}
			
			if(rs != null) {
				System.out.println("problem reading the database.");
				try {
					rs.close();
				}catch(SQLException sqlex) {
					sqlex.printStackTrace();
				}
			}
		}
		
	}

}
