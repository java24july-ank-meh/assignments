package com.revature.dao;

import com.revature.domain.*;
import com.revature.exceptions.*;
import com.revature.util.ConnectionUtil;

import java.util.*;
import java.io.*;
import java.sql.*;

public class DataService implements DAO {

	private boolean doesUserExists(String name) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BANK_USER where user_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) return true;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean createUser(User user) throws ExistingUserException {
		PreparedStatement pstmt = null;
		String username = user.getUsername();
		String password = user.getPassword();
		
		if(doesUserExists(username)) throw new ExistingUserException("User already exists");
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO BANK_USER(user_name,user_password) VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			int numRows = pstmt.executeUpdate();
			if(numRows > 0) return true;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteUser(User user) throws UserNotFoundException{
		PreparedStatement pstmt = null;
		
		if(!doesUserExists(user.getUsername())) throw new UserNotFoundException("User not found");
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM BANK_USER WHERE user_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			int numRows = pstmt.executeUpdate();
			if(numRows > 0) return true;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void readUser(User user) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BANK_USER WHERE user_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setId(rs.getInt("user_id"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean verifyUser(User user) throws UserNotFoundException, IncorrectPasswordException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BANK_USER WHERE user_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				if(user.getPassword().equals(rs.getString("user_password"))){
					user.setId(rs.getInt("user_id"));
					return true;
				} else throw new IncorrectPasswordException("Password authentication failed");
			} else throw new UserNotFoundException("User not found");
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<User> readAllUsers() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BANK_USER";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getString("user_name"),null);
				user.setId(rs.getInt("user_id"));
				users.add(user);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void createAccount(Account account) {
		PreparedStatement pstmt = null;
		int user_id = account.getUserId();
		String account_name = account.getName();
		if(user_id == -1) return;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO BANK_ACCOUNT(account_name,account_balance,user_id) VALUES(?,0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account_name);
			pstmt.setInt(2, user_id);
			pstmt.executeUpdate();	
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void UpdateAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(Account account) throws AccountNotFoundException, AccountNotEmptyException{
		PreparedStatement pstmt = null;
		
		readAccount(account);
		if(account.getId() == -1) throw new AccountNotFoundException("Account not found");
		if(account.getBalance() != 0) throw new AccountNotEmptyException("Account must be empty before deleting it");
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM BANK_ACCOUNT WHERE account_name = ? AND user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account.getName());
			pstmt.setInt(2, account.getUserId());
			pstmt.executeUpdate();	
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void readAccount(Account account) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BANK_ACCOUNT WHERE account_name = ? AND user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account.getName());
			pstmt.setInt(2, account.getUserId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				account.setId(rs.getInt("account_id"));
				account.setBalance(rs.getDouble("account_balance"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Account> readAllAccounts(User user) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Account> accounts = new ArrayList<Account>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM BANK_ACCOUNT WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,user.getId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Account account = new Account(rs.getString("account_name"),user.getId());
				account.setBalance(rs.getDouble("account_balance"));
				account.setId(rs.getInt("account_id"));
				accounts.add(account);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public void withdraw(Account account, double amount) throws OverdraftException {
		CallableStatement cs = null;
		
		readAccount(account);
		if(account.getBalance()-amount < 0.0) throw new OverdraftException("Balance cannot be below zero");
		
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL WITHDRAW(?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, account.getName());
			cs.setInt(2, account.getUserId());
			cs.setDouble(3, amount);
			cs.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(cs != null) {
				try {
					cs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deposit(Account account, double amount) {
		CallableStatement cs = null;
		
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL DEPOSIT(?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, account.getName());
			cs.setInt(2, account.getUserId());
			cs.setDouble(3, amount);
			cs.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
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
