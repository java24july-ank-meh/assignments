package com.revature.alvarado.alejandro.bank.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.alvarado.alejandro.bank.BankUser;
import com.revature.alvarado.alejandro.bank.util.ConnectionUtil;

public class BankUserDAOImpl implements BankUserDAO{
	
	@Override
	public List<BankUser> getAllBankUsers() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BankUser> users = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM BANKUSER";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Integer userId = rs.getInt("BANKUSER_ID");
				String userName = rs.getString("BANKUSER_NAME");
				Boolean isAdmin = 
						rs.getString("BANKUSER_ISADMIN").equalsIgnoreCase("Y") ? true: false;
				String password = rs.getString("BANKUSER_PASSWORD");
				
				BankUser user = new BankUser(userId, userName, isAdmin, password);
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close();} catch (SQLException e) { e.printStackTrace();}
			}
			if (rs != null) {
				try { rs.close();} catch (SQLException e) { e.printStackTrace();}
			}
		}
		
		return users;
	} 

	@Override
	public BankUser getBankUser(Integer userId)
			throws UserNotFoundException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		BankUser user = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE BANKUSER_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				String userName = rs.getString("BANKUSER_NAME");
				Boolean isAdmin = 
						rs.getString("BANKUSER_ISADMIN").equalsIgnoreCase("Y") ? true: false;
				String password = rs.getString("BANKUSER_PASSWORD");
				
				user = new BankUser(userId, userName, isAdmin, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close();} catch (SQLException e) { e.printStackTrace();}
			}
			if (rs != null) {
				try { rs.close();} catch (SQLException e) { e.printStackTrace();}
			}
		}
		
		if (user == null) {
			throw new UserNotFoundException("User was not able to be found");
		}
		return user;
	}

	@Override
	public Boolean createBankUser(BankUser user) {
		PreparedStatement ps = null;
		int rs = 0;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO BANKUSER (BANKUSER_NAME, BANKUSER_ISADMIN, BANKUSER_PASSWORD) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getName());
			String isAdmin = user.getIsAdmin() ? "T": "F";
			ps.setString(2, isAdmin);
			ps.setString(3, user.getPassword());
			
			rs = ps.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close();} catch (SQLException e) { e.printStackTrace();}
			}
		}
		
		return rs > 0;
	}

	@Override
	public Boolean updateBankUser(BankUser user)  {
		PreparedStatement ps = null;
		int rs = 0;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE BANKUSER SET BANKUSER_NAME = ?, BANKUSER_ISADMIN = ?, BANKUSER_PASSWORD = ? WHERE BANKUSER_ID = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getName());
			String isAdmin = user.getIsAdmin() ? "T": "F";
			ps.setString(2, isAdmin);
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getUserId());
			
			rs = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close();} catch (SQLException e) { e.printStackTrace();}
			}
		}
		
		return rs > 0;
	}

	@Override
	public Boolean deleteBankUser(Integer userId) {
		PreparedStatement ps = null;
		int rs = 0;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM BANKUSER WHERE BANKUSER_ID = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userId);
			
			rs = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close();} catch (SQLException e) { e.printStackTrace();}
			}
		}
		
		return rs > 0;
	}

	@Override
	public BankUser signInBankUser(String name, String userPassword) 
			throws UserNotFoundException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		BankUser user = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE BANKUSER_NAME = ? AND BANKUSER_PASSWORD = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, userPassword);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int userId = rs.getInt("BANKUSER_ID");
				String userName = rs.getString("BANKUSER_NAME");
				Boolean isAdmin = 
						rs.getString("BANKUSER_ISADMIN").equalsIgnoreCase("Y") ? true: false;
				String password = rs.getString("BANKUSER_PASSWORD");
				
				user = new BankUser(userId, userName, isAdmin, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close();} catch (SQLException e) { e.printStackTrace();}
			}
			if (rs != null) {
				try { rs.close();} catch (SQLException e) { e.printStackTrace();}
			}
		}
		
		if (user == null) {
			throw new UserNotFoundException("User was not able to be found");
		}
		return user;
	}
}
