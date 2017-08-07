package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.domain.Account;
import com.revature.domain.AccountTypes;
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
			pstmt.setByte(6, (byte)0);
			
			int rowsChanged = pstmt.executeUpdate();
			System.out.println(rowsChanged + " row(s) added to BANKUSERS");
			
			String sql2 = "{CALL GET_USERID_FROM_USERNAME(?,?)}";
			CallableStatement cs = conn.prepareCall(sql2);
			cs.setString(1, b.getUsername());
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			
			b.setId(cs.getInt(2));
			
		}catch(SQLException e) {
			System.out.println("Failed to create user");
			e.printStackTrace();
		}
	}

	@Override
	public void createAccount(BankUser b, Account a) {
		
		PreparedStatement pstmt = null;
		
		try(Connection conn = ConnectionUtil.getConnectionProp()){
		
			String sql = "INSERT INTO ACCOUNTS (ACCOUNTTYPE,USERID,BALANCE,INTERESTRATE) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getAccountType());
			pstmt.setInt(2, b.getId());
			pstmt.setDouble(3, a.getBalance());
			pstmt.setDouble(4,a.getInterestRate());
			
			int rowsChanged = pstmt.executeUpdate();
			System.out.println(rowsChanged+" added to ACCOUNTS");
			
			String sql2 = "{CALL ACCOUNT_KEY(?)}";
			CallableStatement cs = conn.prepareCall(sql2);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			
			a.setId(cs.getInt(1));
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAccount(BankUser b, Account a) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM ACCOUNTS WHERE ACCOUNTID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getId());
			int rowsChanged = pstmt.executeUpdate();
			System.out.println(rowsChanged + " account(s) deleted");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//Make existing user a super user, or forfeit superUser priveleges
	@Override
	public void createSuperUser(BankUser b, boolean forfeit) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE BANKUSERS SET ISSUPER=? WHERE USERID=?";
			pstmt = conn.prepareStatement(sql);
			if(forfeit) {pstmt.setString(1, "0");}
			else {pstmt.setString(1, "1");}
			pstmt.setInt(2, b.getId());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void transaction(BankUser b, Account a, double amount) {
		
		double newBalance = a.getBalance() + amount;
		if(newBalance < 0.0) {
			System.out.println("Insufficient funds");
			return;
		}
		a.setBalance(newBalance);
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE ACCOUNTS SET BALANCE=? WHERE ACCOUNTID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, newBalance);
			pstmt.setInt(2, a.getId());
			int rowsChanged = pstmt.executeUpdate();
			System.out.println(rowsChanged+" account(s) changed");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double viewBalance(BankUser b, Account a) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNTID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getDouble("BALANCE");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void deleteBankUser(SuperUser su, BankUser b) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM BANKUSERS WHERE USERID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getId());
			int rowsChanged = pstmt.executeUpdate();
			System.out.println(rowsChanged + " user(s) deleted");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Map<Integer, BankUser> viewAllUsers() {
		
		PreparedStatement pstmt = null;
		Map<Integer, BankUser> allUsers = new HashMap<>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BANKUSERS";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boolean isSuper = rs.getString("ISSUPER").equals("1");
				BankUser nextUser = null;
				if(isSuper) {
					nextUser = SuperUser.createSuperUser(rs.getString("USERNAME"), 
							rs.getString("PASS"), rs.getString("FIRSTNAME"), 
							rs.getString("LASTNAME"));
				}
				else {
					nextUser = new BankUser(rs.getString("USERNAME"), rs.getString("PASS"),
							rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
				}
				nextUser.setId(rs.getInt("USERID"));
				allUsers.put(nextUser.getId(), nextUser);
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
			
			String isSuper = cs.getString(6);
			if(!isSuper.equals("0")) {
				existingUser = SuperUser.createSuperUser(username, pass, cs.getString(4), 
					cs.getString(5));
			}
			else {
				existingUser = new BankUser(username, pass, cs.getString(4), 
					cs.getString(5));
			}
			
		existingUser.setId(cs.getInt(3));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return existingUser;
	}

	@Override
	public boolean isLoggedIn(BankUser b) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT LOGGEDIN FROM BANKUSERS WHERE USERNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getUsername());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String result = rs.getString("LOGGEDIN");
				if(result.equals("1")) {return true;}
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> viewMyAccounts(BankUser b) {
		List<Account> accs = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ACCOUNTS WHERE USERID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int accountId = rs.getInt(1);
				String accountType = rs.getString(2);
				int userId = rs.getInt(3);
				double balance = rs.getDouble(4);
				double interestRate = rs.getDouble(5);
				
				Account newAccount = new Account(b, balance, accountType, interestRate);
				newAccount.setId(accountId);
				accs.add(newAccount);
				b.addAccount(newAccount);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return accs;
	}
	
	public void populateAccountTypes() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ACCOUNTTYPES";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String nextType = rs.getString(1);
				AccountTypes.addType(nextType);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUserField(BankUser b, String field, String value) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "";
			if(field.equalsIgnoreCase("firstname")) {
				sql = "UPDATE BANKUSERS SET FIRSTNAME=? WHERE USERID=?";
			}else if(field.equalsIgnoreCase("lastname")) {
				sql = "UPDATE BANKUSERS SET LASTNAME=? WHERE USERID=?"; 
			}else if(field.equalsIgnoreCase("username")) {
				sql = "UPDATE BANKUSERS SET USERNAME=? WHERE USERID=?";
			}else if(field.equalsIgnoreCase("password")) {
				sql = "UPDATE BANKUSER SET PASSWORD=? WHERE USERID=?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			pstmt.setInt(2, b.getId());
			
			int rowsChanged = pstmt.executeUpdate();
			System.out.println(rowsChanged + " users changed");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int userCount() {
		Statement stmt = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "SELECT COUNT(*) FROM BANKUSERS";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				return rs.getInt(1);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int accountCount() {
		
		Statement stmt = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "SELECT COUNT(*) FROM ACCOUNTS";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				return rs.getInt(1);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	
	}
}
