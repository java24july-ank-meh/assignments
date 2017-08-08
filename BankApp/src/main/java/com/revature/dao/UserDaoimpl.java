package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.connections.ConnectionUtil;
import com.revature.bean.User;


public class UserDaoimpl implements UserDao {

	
	public void createNewUser(String username, String password, int isAdmin) {
		// Opening a new connection
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			// CallableStatement: {CALL PROCEDURE_NAME(?)}
			String sql = "{CALL CREATE_USER(?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, password);
			cs.setInt(3, isAdmin);	
			
			Boolean result = cs.execute();
			if (result)
				System.out.println("failed");
			else
				System.out.println("Added user");
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

	public void createAccount(int U_id, String accName) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			// CallableStatement: {CALL PROCEDURE_NAME(?)}
			String sql = "{CALL CREATE_ACCOUNT(?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, U_id);
			cs.setString(2, accName);		
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

	public void deleteUser(int U_id) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			// CallableStatement: {CALL PROCEDURE_NAME(?)}
			String sql = "{CALL DELETE_USER(?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, U_id);		
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
	
	public void deleteAccount(int accnt_ID) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			// CallableStatement: {CALL PROCEDURE_NAME(?)}
			String sql = "{CALL DELETE_ACCOUNT(?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, accnt_ID);		
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

	public void validLogin(String username, String password) {
		// TODO Auto-generated method stub
	}

	public void validAdminLogin(String username, String password, int x) {
		// TODO Auto-generated method stub	
	}
	
	
	public void getBalance(int accnt_ID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double bal;
		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			String sql = "SELECT AC_BALANCE FROM ACCOUNTS WHERE AC_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accnt_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bal = rs.getDouble("AC_BALANCE");
				System.out.println(bal); 				
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
	public void Deposit(int accnt_ID, double amt) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			// CallableStatement: {CALL PROCEDURE_NAME(?)}
			String sql = "{CALL DEPOSIT(?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, accnt_ID);	
			cs.setDouble(2, amt);
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
	
	public User read_user(String username) throws IOException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			String sql = "SELECT * FROM BANK_USER WHERE U_NAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int Uid = rs.getInt("U_ID");
				String Username = rs.getString("U_NAME");
				int IsAdmin = rs.getInt("IS_ADMIN");
				String PassWord = rs.getString("U_PASSWORD");
				
				User user1 = new User(Username, PassWord, IsAdmin, Uid);
				return user1;				
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
		return null;

	}
	
}
