package com.revature.dao;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

import com.revature.domain.User;
import com.revature.exception.OverdraftException;
import com.revature.exception.SQLStatementFailedException;
import com.revature.exception.UsernameTakenException;
import com.revature.util.ConnectionUtil;

public class BankingDAOImpl implements BankingDAO {

	@Override
	public void createAccount(String accountName, User u) {
		// opening a new connection
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO BANKACCOUNT(USERID, BANKACCOUNTNAME) VALUES(?, ?)");
			pstmt.setInt(1, u.getUserid());
			pstmt.setString(2, accountName);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void viewAccounts(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM BANKACCOUNT WHERE USERID = ?");
			pstmt.setInt(1, u.getUserid());
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Accounts:");
			while (rs.next()) {
				String accname = rs.getString("BANKACCOUNTNAME");
				Double total = rs.getDouble("TOTAL");
				System.out.println(accname + ": " + total);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public User checkLogin(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			Properties prop = new Properties();
			prop.load(new FileInputStream("connection.properties"));

			String superUser = prop.getProperty("superuser");
			String superPass = prop.getProperty("superpass");
			if (username.equals(superUser) && password.equals(superPass))
				return new User("super");

			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM BANKUSER WHERE USERNAME = ? AND PASSWORD = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("Successful login as " + username);
				return new User(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("USERID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Username or password was incorrect.");
		return null;
	}

	@Override
	public void registerUser(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO BANKUSER(USERNAME, PASSWORD) VALUES(?, ?)");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public double updateAccount(String accname, double amount, User u) throws OverdraftException, SQLException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("SELECT TOTAL FROM BANKACCOUNT WHERE BANKACCOUNTNAME = ? AND USERID = ?");
			pstmt.setString(1, accname);
			pstmt.setInt(2, u.getUserid());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				if (rs.getDouble("TOTAL") + amount < 0)
					throw new OverdraftException("Insufficient funds, withdraw canceled.");
			CallableStatement cstmt = conn.prepareCall("{CALL SP_UPDATEACCOUNT(?, ?, ?, ?)}");
			cstmt.setString(1, accname);
			cstmt.setDouble(2, amount);
			cstmt.setInt(3, u.getUserid());
			cstmt.registerOutParameter(4, java.sql.Types.DECIMAL);
			cstmt.executeUpdate();
			return cstmt.getDouble(4);
		} catch(SQLException e) {
			throw e;
		} catch (OverdraftException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}

	@Override
	public void deleteAccount(String accname, User u) throws SQLStatementFailedException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM BANKACCOUNT WHERE BANKACCOUNTNAME = ? AND USERID = ? AND TOTAL = 0");
			pstmt.setString(1, accname);
			pstmt.setInt(2, u.getUserid());
			if (pstmt.executeUpdate() < 1) {
				throw new SQLStatementFailedException(
						"Error deleting account. Please ensure the account was spelled correctly and that it empty.");
			}
		} catch (SQLStatementFailedException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(String username) throws SQLStatementFailedException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM BANKUSER WHERE USERNAME = ?");
			pstmt.setString(1, username);
			if (pstmt.executeUpdate() < 1) {
				throw new SQLStatementFailedException(
						"Error deleting user. Please ensure the username was spelled correctly.");
			}
		} catch (SQLStatementFailedException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void checkUsernameAvail(String username) throws UsernameTakenException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE USERNAME = '" + username + "'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				throw new UsernameTakenException("Username is taken.");
			}
		} catch (UsernameTakenException e) {
			throw e;
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void viewUsers() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM BANKUSER");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Accounts:");
			while (rs.next()) {
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				System.out.println("Username: " + username + ", Password:  " + password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(String user, String newUser, String newPass) throws SQLStatementFailedException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn
					.prepareCall("UPDATE BANKUSER SET USERNAME = ?, PASSWORD = ? WHERE USERNAME = ?");
			pstmt.setString(1, newUser);
			pstmt.setString(2, newPass);
			pstmt.setString(3, user);
			if (pstmt.executeUpdate() < 1)
				throw new SQLStatementFailedException("Error accessing user. Was it spelled correctly?");
		} catch (SQLStatementFailedException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
