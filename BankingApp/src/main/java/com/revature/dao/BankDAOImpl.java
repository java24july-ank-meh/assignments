package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Account;
import com.revature.domain.BankUser;
import com.revature.domain.SuperUser;
import com.revature.util.ConnectionUtil;

public class BankDAOImpl implements BankDAO{

	@Override
	public void createBankUser(BankUser b) {
		
		PreparedStatement pstmt = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String username = b.getUsername();
			String pass = b.getPass();
			String firstname = b.getFirstname();
			String lastname = b.getLastname();
			
			String sql = "INSERT INTO BANKUSERS (USERNAME,PASS,FIRSTNAME,LASTNAME,ISSUPER,LOGGEDIN) VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, pass);
			pstmt.setString(3, firstname);
			pstmt.setString(4, lastname);
			pstmt.setByte(5, (byte)0);
			pstmt.setByte(6, (byte)1);
			
			int rowsChanged = pstmt.executeUpdate();
			System.out.println(rowsChanged + " rows changed");
			
			
		}catch(SQLException e) {
			System.out.println("Failed to create user");
			e.printStackTrace();
		}
		
	}

	@Override
	public void createAccount(BankUser b, Account a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createSuperUser(BankUser b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(BankUser b, Account a, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double viewBalance(BankUser b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteBankUser(SuperUser su) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BankUser> viewAllUsers() {
		
		PreparedStatement pstmt = null;
		List<BankUser> allUsers = new ArrayList<>();
		
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
		
		return allUsers;
	}

	@Override
	public List<Account> viewAllAccounts(SuperUser su) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void login(BankUser b) {
		CallableStatement cs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL LOGIN(?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, b.getId());
			cs.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void logout(BankUser b) {
		CallableStatement cs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL LOGOUT(?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, b.getId());
			cs.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public BankUser getUserFromInfo(String username, String pass) {
		CallableStatement cs = null;
		BankUser existingUser = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL GET_USER_FROM_INFO(?,?,?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, pass);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			
			cs.execute();
			ResultSet rs = cs.getResultSet();
			int isSuper = rs.getInt(3);
			if(isSuper>0) {
				existingUser = SuperUser.createSuperUser(username, pass, rs.getString("FN"), 
						rs.getString("LN"));
			}
			else {
				existingUser = new BankUser(username, pass, rs.getString("FN"), 
						rs.getString("LN"));
			}
			
			existingUser.setId(rs.getInt("U"));
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return existingUser;
	}

}
