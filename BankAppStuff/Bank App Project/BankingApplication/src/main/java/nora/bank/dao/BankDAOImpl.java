package nora.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nora.bank.domain.AppUser;
import nora.bank.domain.BankAccount;
import nora.bank.domain.User;
import nora.bank.util.ConnectionUtil;

public class BankDAOImpl implements BankDAO {

	@Override
	public void createUser(User u) {
		// opening new connection
		try (Connection conn = ConnectionUtil.getConnection()) {
			// Storing class fields as future arguments
			String un = u.getUsername();
			String fn = u.getFirstName();
			String ln = u.getLastName();
			String pass = u.getPassword();
			

			// declare SQL statement + arguments
			String sql = "INSERT INTO BANKUSER (username, password, FirstName, LastName, User_Type)" +
			" VALUES('" + un + "', '" + pass + "', '" + fn + "', '" + ln + "', " + User.NORMAL_USER + ")";

			// Create the statement as part of the connection
			Statement stmt = conn.createStatement();

			// Execute the statement
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void createBankAccount(int userID) {
		// INSERT INTO account (Account_Holder_ID, Balance) VALUES(?, 0);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO account (Account_Holder_ID, Balance) VALUES(?, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try { pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try { rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}

	}

	@Override
	public void withdraw(int accountID, int amt) {
		//checking to ensure that there is enough money in the account for this
		//withdraw is done before this method is called
		deposit(accountID, -1 * amt);

	}

	@Override
	public void deposit(int accountID, int amt) {
		CallableStatement cs = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{CALL SP_DEPOSIT(?, ?, ?)}";
			
			cs = conn.prepareCall(sql);
			
			//set arguments
			cs.setInt(1,  accountID);
			cs.setInt(2,  amt);
			cs.registerOutParameter(3, java.sql.Types.INTEGER);
			
			//execute (returns boolean)
			cs.execute();
			int newBalance = cs.getInt(3);
			
			System.out.println("Your new balance in this account is now $" + newBalance);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * This method attempts to log the user in with the provided username and password.
	 * Exceptions are thrown if the username does not exist or the password does not match
	 * If an exception is thrown, false is returned.
	 */
	@Override
	public boolean logUserIn(String username, String password) {
		AppUser appUser = new AppUser();
		User user = appUser.getUser();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//check to make sure that the user with designated username exists with this boolean
		boolean usernameExists = false;
		boolean passwordMatch = false;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE USERNAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				// rs.next only returns true if there is at least one thing returned.
				usernameExists = true;
				
				//now that we know the username exists, we need to know if the passwords match
				if(!password.equals(rs.getString("PASSWORD"))) throw new Exception("The password you entered did not match.");
				passwordMatch = true;
				
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setUserID(rs.getInt("USER_ID"));
				user.setUserType(rs.getInt("User_Type"));
				user.setUsername(username);
				
			}
			
			//the following exception is thrown if the block in the while loop is never triggered
			//which can only happen if the sql statement returns nothing
			if(!usernameExists) throw new Exception("The username you have entered does not exist.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) {try { pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try { rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
		if(passwordMatch && usernameExists) return true;
		else return false;
		
	}

	
	@Override
	public boolean isUsernameTaken(String username) {
		boolean isTaken = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE USERNAME = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isTaken = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) {try { pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try { rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
		return isTaken;
	}

	
	@Override
	public List<BankAccount> getUserAccountsAndBalances(String username) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BankAccount> accounts = new ArrayList<>();

		//get the user ID for making new bank account objects
		AppUser user = new AppUser();
		int userID = user.getUser().getUserID();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM VW_USERACCOUNTS where username = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			
			boolean hasAccounts = false;
			
			//go through and add all accounts returned from the query to the array list
			while(rs.next()) {
				hasAccounts = true;
				
				int bankAccountID = rs.getInt("BANK_ACCOUNT_ID");
				int balance = rs.getInt("BALANCE");
				
				BankAccount account = new BankAccount(bankAccountID, userID, balance);
				
				accounts.add(account);
				
			}
			
			if(!hasAccounts) System.out.println("You have no bank accounts associated with you.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) {try { pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try { rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		return accounts;
	}

	@Override
	public void deleteBankAccount(int accountID) {
		//DELETE FROM Account WHERE Bank_Account_ID = ?
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM Account WHERE Bank_Account_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountID);
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try { pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try { rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
	}

	
	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM BANKUSER";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserID(rs.getInt("USER_ID"));
				user.setUsername(rs.getString("USERNAME"));
				user.setUserType(rs.getInt("USER_TYPE"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try { pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try { rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
		return users;
		
	}

	@Override
	public void deleteUser(int accountID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM BankUser WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountID);
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try { pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try { rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
	}

	@Override
	public void editExistingUser(User u) {
		PreparedStatement pstmt = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			// declare SQL statement + arguments
			String sql = "UPDATE BANKUSER SET USERNAME = ?, Password = ?, FirstName = ?, LastName = ? " +
					"WHERE USER_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getFirstName());
			pstmt.setString(4, u.getLastName());
			pstmt.setInt(5, u.getUserID());

			// Execute the statement
			pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
