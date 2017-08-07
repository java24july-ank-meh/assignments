package com.banking.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banking.domain.Account;
import com.banking.domain.User;
import com.banking.exception.OverDraftException;
import com.banking.util.ConnectionUtil;

public class BankDaoImpl implements BankDao {

	public void deleteAccount(int id, String name) throws IOException {
		PreparedStatement pstmt = null;
		int ra = 0;
		try{
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "DELETE FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_NAME = ?";	
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			ra = pstmt.executeUpdate();
			if(ra > 0)
				System.out.println("Successfully deleted: " + name);
			else
				System.out.println("No existing account with name: " + name);
		} catch (SQLException e) {
			System.out.println("No account with the given name exists");
		} finally {
			if(pstmt!=null) {
				try {pstmt.close();}
				catch(SQLException e) {System.out.println("Failed to close defer to Administrator");
				}
			}
		}
	}

	public double depositAmount(int id, String name, double i) throws IOException {
		CallableStatement cs = null;
		double total = 0;
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "{CALL ADD_TO_AMOUNT(?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.setDouble(3, i);
			cs.registerOutParameter(4, java.sql.Types.NUMERIC);
			boolean rs = cs.execute();
			total = cs.getDouble(4);
			if(!rs)
				System.out.println("Successfully deposited: $" + i);
			System.out.println("New total for account " + name + " is $" + total);
			cs.close();
		} catch (SQLException e) {
				System.out.println("This account does not exist");
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					System.out.println("Failed to close defer to Administrator");
				}
			
		}
		return total;
	}
	
	public double accountTotal(int user_id, String name) throws SQLException, IOException {
		PreparedStatement pstmt = null;
		double d = 0;

			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT AMOUNT FROM ACCOUNTS WHERE ACCOUNT_NAME = ? AND USER_ID = ?";
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, name);
			pstmt.setInt(user_id, 2);
			ResultSet rs = pstmt.executeQuery();
				d = rs.getDouble("AMOUNT");
				
			rs.close();

		return d;
	}
	
	public double withdrawAmount(int id, String name, double i) throws IOException, OverDraftException {
		CallableStatement cs = null;
		double total = 0;
		BankDao dao = new BankDaoImpl();
		try {
			if(dao.depositAmount(id, name, 0) < i) {
				throw new OverDraftException();
			}
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "{CALL SUBTRACT_FROM_AMOUNT(?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.setDouble(3, i);
			cs.registerOutParameter(4, java.sql.Types.NUMERIC);
			boolean rs = cs.execute();
			total = cs.getDouble(4);
			if(!rs) {
				System.out.println("Withdraw successful");
				System.out.println("New total for account " + name + " is $" + total);
			}
			cs.close();
		} catch (OverDraftException e) {
			System.out.println(e.getMessage());
		}catch (SQLException e) {
			System.out.println("The account specified does not exist");
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return total;
	}

	public List viewAllAccounts(User u) throws IOException {
		PreparedStatement pstmt = null;
		List<Account> accounts = new ArrayList<Account>();
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT * FROM ACCOUNTS WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getUserId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int accountId =rs.getInt("BANK_ACCOUNT_ID");
				int userId = rs.getInt("USER_ID");
				String name = rs.getString("ACCOUNT_NAME");
				int amount = rs.getInt("AMOUNT");
				Account a = new Account(accountId, userId, name, amount);
				accounts.add(a);
			}
			if(accounts.isEmpty())
				System.out.println("You have no accounts");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {pstmt.close();}
				catch(SQLException e) {e.printStackTrace();
				}
			}
		}
		return accounts;
	}

	public void createAccount(Account a) throws IOException {
		CallableStatement cs = null;
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "{CALL ADD_ACCOUNT(?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, a.getUserId());
			cs.setString(2, a.getName());
			cs.setDouble(3, a.getAmount());
			boolean rs = cs.execute();
			if (rs)
				System.out.println("Account Failed to Create");
			else
				System.out.println("Account Created");
			
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public List<User> viewUsers() throws IOException {
		PreparedStatement pstmt = null;
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT * FROM BANK_USERS";
			pstmt = conn.prepareCall(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int userId = rs.getInt("USER_ID");
				String username =rs.getString("USERNAME");
				String password =rs.getString("PASSCODE");
				int super_num = rs.getInt("SUPER");
				
				User u = new User(userId, username, password, super_num);
				users.add(u);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {pstmt.close();}
				catch(SQLException e) {e.printStackTrace();
				}
			}
		}
		return users;
	}

	public void createUser(String name, String password, int superuser) throws IOException {
		CallableStatement cs = null;
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "{CALL ADD_USER(?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, name);
			cs.setString(2, password);
			cs.setInt(3, superuser);
			boolean rs = cs.execute();
			if (rs)
				System.out.println("Failed to create user");
			else
				System.out.println("User created");
			
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void deleteUser(int id) throws IOException {
		PreparedStatement pstmt = null;
		int ra = 0;
		try{
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "DELETE FROM BANK_USERS WHERE USER_ID = ?";	
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			ra = pstmt.executeUpdate();
			if(ra > 0)
				System.out.println("User deleted");
			else
				System.out.println("No user exists with given input id");
		} catch (SQLException e) {
			System.out.println("Invalid user ID");
		} finally {
			if(pstmt!=null) {
				try {pstmt.close();}
				catch(SQLException e) {e.printStackTrace();
				}
			}
		}

	}

	public void updateUser(int id, String username, String password, int superuser) throws IOException {
		PreparedStatement pstmt = null;
		int ra = 0;
		try{
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "UPDATE BANK_USERS SET USERNAME = ?, PASSCODE = ?, SUPER = ? WHERE USER_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setInt(3, superuser);
			pstmt.setInt(4, id);
			ra = pstmt.executeUpdate();
			if(ra > 0)
				System.out.println("User updated");
			else
				System.out.println("No user exists for given id");
		} catch (SQLException e) {
			System.out.println("Invalid user ID");
		} finally {
			if(pstmt!=null) {
				try {pstmt.close();}
				catch(SQLException e) {e.printStackTrace();
				}
			}
		}
	}

	public User login(String username, String password) throws IOException {
		CallableStatement cs = null;
		int user_id;
		int super_id = 0;
		User u = new User();
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "{CALL LOGIN(?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setString(2, username);
			cs.setString(3, password);
			cs.registerOutParameter(4, java.sql.Types.NUMERIC);
			cs.registerOutParameter(1, java.sql.Types.NUMERIC);
			boolean rs = cs.execute();
			user_id = cs.getInt(1);
			super_id = cs.getInt(4);
			u = new User(user_id, username, password, super_id);
			
			cs.close();
		} catch (SQLException e) {
			System.out.println("Invalid username or password");
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return u;
		
	}

}
