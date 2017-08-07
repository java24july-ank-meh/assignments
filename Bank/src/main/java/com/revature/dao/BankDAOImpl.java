package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.AccountUser;
import com.revature.util.ConnectionUtil;

@SuppressWarnings("unused")
public class BankDAOImpl implements BankDAO {

	@Override
	public void deleteAccountUser(int id) {
		// Opening a new connection
		try (Connection conn = ConnectionUtil.getConnection()) {

			// Declare sql statement + arguments
			String sql = "ALTER TABLE BANK_ACCOUNT\n" + "    DROP CONSTRAINT  FK_ACCOUNT_ID ;\n" + "  \n"
					+ "DELETE FROM  ACCOUNT_USER \n" + "    WHERE USER_ID = " + id;
			System.out.println(sql);
			// Create the statement, as part of the connection
			Statement stmt = conn.createStatement();

			// Execute the statement, return number of rows affected
			int nRowsAffected = stmt.executeUpdate(sql);

			System.out.println(nRowsAffected + " row deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Account user with id: " + id + " has been deleted!");

	}

	@Override
	public void initAccountUser(AccountUser u) {
		// Storing class fields as future arguments
		String n = u.getUserName();
		String p = u.getPassword();

		// Opening a new connection
		try (Connection conn = ConnectionUtil.getConnection()) {

			// Declare sql statement + arguments
			String sql2 = "SELECT USER_ID FROM ACCOUNT_USER WHERE USERNAME = '" + n + "' AND PASSWORD = '" + p + "'";
			System.out.println(sql2);
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery(sql2);
			
			int userid = 0;
			int isSupe = 0;
			while (rs.next()) {
				userid = rs.getInt("USER_ID");
				isSupe = rs.getInt("SUPER");
			}
			u.setId(userid);
			u.setSuper(isSupe);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Account user with username: " + n + " initialized");

	}

	@Override
	public void createAccountUser(AccountUser u) {
		// Storing class fields as future arguments
		String n = u.getUserName();
		String p = u.getPassword();
		int s = 0;
		if(u.isSuper() == true) {s = 1;}
		
		// Opening a new connection
		try (Connection conn = ConnectionUtil.getConnection()) {
			// Declare sql statement + arguments
			String sql = "INSERT INTO ACCOUNT_USER (USERNAME, PASSWORD, SUPER) VALUES ('" + n + "', '" + p + "' , " + s + ")";
			System.out.println(sql);
			// Create the statement, as part of the connection
			Statement stmt = conn.createStatement();

			// Execute the statement
			stmt.executeUpdate(sql);

			String sql2 = "SELECT USER_ID FROM ACCOUNT_USER WHERE USERNAME = '" + n + "' AND PASSWORD = '" + p + "'";
			System.out.println(sql2);
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery(sql2);

			int userid = 0;
			while (rs.next()) {
				userid = rs.getInt("USER_ID");
			}
			u.setId(userid);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Account user with username: " + n + " has been created!");

	}

	@Override
	public void readAccountUser(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT_USER WHERE USER_ID = ?";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int userid = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
				String pass = rs.getString("PASSWORD");

				System.out.println("User_ID: " + userid);
				System.out.println("UserName: " + username);
				System.out.println("Password: " + pass);
				System.out.println("");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public List<AccountUser> readAllAccountUser() {
		PreparedStatement pstmt = null;
		List<AccountUser> accountUsers = new ArrayList<>();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM ACCOUNT_USER";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("USER_ID");
				String name = rs.getString("USERNAME");
				String pass = rs.getString("PASSWORD");

				AccountUser u = new AccountUser(id, name, pass);
				accountUsers.add(u);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("");
		System.out.println("All account users:");
		return accountUsers;
	}

	@Override
	public void withdraw(int bid, int amt) {
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SP_DEPOSIT(" + bid + "," + amt + ")";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(amt + "monies have been withdrawn from bank account with id: " + bid);
	}

	@Override
	public void deposit(int bid, int amt) {
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SP_WITHDRAW(" + bid + "," + amt + ")";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(amt + "monies deposited into bank account with id: " + bid);
	}

	@Override
	public void viewBaccounts(AccountUser u) {
		// prepare variables needed for later use
		PreparedStatement pstmt = null;
		int id = u.getId();

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM BANK_ACCOUNT WHERE USER_ID = " + id;
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("________________________________________");
			System.out.println("Bank Accounts of user: " + u.getUserName());
			while (rs.next()) {
				String bid = rs.getString("BANK_ACCOUNT_ID");
				int bal = rs.getInt("BALANCE");

				System.out.println("BANK_ACCOUNT_ID: " + bid + " BALANCE : " + bal);
			}
			System.out.println("________________________________________");
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void newBaccount(int amt, int id) {
		PreparedStatement pstmt = null;

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO BANK_ACCOUNT ( BALANCE, USER_ID ) VALUES (" + amt + ", " + id + ")";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println(
				"Created new bank account for user with id: " + id + " with initial amount of: " + amt + " monies");
	}

	@Override
	public void deleteBaccount(int id) {
		PreparedStatement pstmt = null;

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "DELETE FROM  BANK_ACCOUNT  WHERE BANK_ACCOUNT_ID = " + id;
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Deleted bank account with id: " + id + "!");

	}

	@Override
	public void deleteAllAcountUsers() {
		// Opening a new connection
		try (Connection conn = ConnectionUtil.getConnection()) {

			// Declare sql statement + arguments
			String sql = "ALTER TABLE BANK_ACCOUNT DROP CONSTRAINT  FK_ACCOUNT_ID ";
			String sql4 = "ALTER TABLE BANK_ACCOUNT ADD CONSTRAINT FK_ACCOUNT_ID FOREIGN KEY (USER_ID) REFERENCES ACCOUNT_USER (USER_ID) ON DELETE CASCADE";
			String sql2 = "TRUNCATE TABLE ACCOUNT_USER DROP STORAGE";
			String sql3 = "TRUNCATE TABLE BANK_ACCOUNT DROP STORAGE";
			
			System.out.println(sql);
			// Create the statement, as part of the connection
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			Statement stmt3 = conn.createStatement();
			Statement stmt4 = conn.createStatement();
			
			
			stmt.execute(sql);
			stmt2.execute(sql2);
			stmt3.execute(sql3);
			stmt4.execute(sql4);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("All users have been deleted!!!!");

	}

}
