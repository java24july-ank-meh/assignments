
package com.doubletrouble.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.doubletrouble.domain.Reimbursements;
import com.doubletrouble.domain.User;
import com.doubletrouble.util.ConnectionUtil;

public class ERSDaoImpl implements ERSDao {	
	
	
	public User login(String username, String password) throws IOException, SQLException {
		CallableStatement cs = null;
		int user_id;
		String user_role;
		User u = new User();
		Connection conn = ConnectionUtil.getConnectionProp();
		try {
			String sql = "{CALL LOGIN(?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, password);
			cs.registerOutParameter(3, java.sql.Types.NUMERIC);
			cs.registerOutParameter(4, java.sql.Types.VARCHAR);
			cs.execute();
			user_id = cs.getInt(3);
			user_role = cs.getString(4);
			u = new User(user_id, username, password, user_role);
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid username or password");
		} finally {
			conn.close();
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return u;
		}
	
	public void requestReimbursement(double amount, String description, int user_id, int type) throws IOException, SQLException {
		CallableStatement cs = null;
		Connection conn = ConnectionUtil.getConnectionProp();
		try {
			String sql = "{CALL REQUEST_REIMB(?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setDouble(1, amount);
			cs.setString(2, description);
			cs.setInt(3, user_id);
			cs.setInt(4, type);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid username or password");
		} finally {
			conn.close();
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		}
	
	
	/*EUID NUMBER, UUSERNAME VARCHAR2, UPASSWORD VARCHAR2, UFIRSTNAME VARCHAR2, ULASTNAME VARCHAR2, UEMAIL VARCHAR2) IS*/
	public void updateUser(int id, String username, String password, String fName, String lName, String email) throws IOException, SQLException {
		CallableStatement cs = null;
		Connection conn = ConnectionUtil.getConnectionProp();
		try {
			String sql = "{CALL UPDATE_USER(?,?,?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.setString(2, username);
			cs.setString(3, password);
			cs.setString(4, fName);
			cs.setString(5, lName);
			cs.setString(6, email);
			cs.execute();
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid choice");
		} finally {
			conn.close();
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
	}

	public User viewUserInfo(int id) throws IOException, SQLException {
		CallableStatement cs = null;
		User u = new User();
		Connection conn = ConnectionUtil.getConnectionProp();
		try {
			String sql = "{CALL VIEW_USER_INFORMATION(?,?,?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.registerOutParameter(2, java.sql.Types.VARCHAR);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			cs.registerOutParameter(4, java.sql.Types.VARCHAR);
			cs.registerOutParameter(5, java.sql.Types.VARCHAR);
			cs.registerOutParameter(6, java.sql.Types.VARCHAR);
			cs.execute();
			String username, password, fName, lName, email;
			username = cs.getString(2);
			password = cs.getString(3);
			fName = cs.getString(4);
			lName = cs.getString(5);
			email = cs.getString(6);
			u = new User(id,username,password,fName,lName,email);
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid choice");
		} finally {
			conn.close();
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return u;
	}
	

	public List<Reimbursements> viewStatusOfReimbursements(int id, int status) throws IOException, SQLException {
		CallableStatement cs = null;
		List<Reimbursements> rbs = new ArrayList<Reimbursements>();
		Connection conn = ConnectionUtil.getConnectionProp();
		try {
			String sql = "{CALL VIEW_PENDING_REIMBURSEMENTS(?, ?, ?, ?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.setInt(2, status);
			cs.registerOutParameter(3, java.sql.Types.NUMERIC);
			cs.registerOutParameter(4, java.sql.Types.VARCHAR);
			cs.registerOutParameter(5, java.sql.Types.TIMESTAMP);
			cs.registerOutParameter(6, java.sql.Types.NUMERIC);
			ResultSet rs = cs.getResultSet();
			Reimbursements rb;
			double amount;
			int type;
			String description, submitTime;
			while(rs.next()) {
			amount = cs.getDouble(3);
			description = cs.getString(4);
			submitTime = cs.getString(5);
			type = cs.getInt(6);
			rb = new Reimbursements(amount, description, type);
			rbs.add(rb);
			}
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid choice");
		} finally {
			conn.close();
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return rbs;
	}
}