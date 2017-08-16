
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
		u.setRole("none");
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

	public void requestReimbursement(double amount, String description, int user_id, int type)
			throws IOException, SQLException {
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

	/*
	 * EUID NUMBER, UUSERNAME VARCHAR2, UPASSWORD VARCHAR2, UFIRSTNAME VARCHAR2,
	 * ULASTNAME VARCHAR2, UEMAIL VARCHAR2) IS
	 */
	public void updateUser(int id, String username, String password, String fName, String lName, String email)
			throws IOException, SQLException {
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
			u = new User(id, username, password, fName, lName, email);
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

	public List<Reimbursements> viewPending(int id) throws IOException, SQLException {
		PreparedStatement cs = null;
		int r_id;
		double r_amount;
		String r_description;
		String r_submitted;
		int r_type;
		int u_id_author;
		List<Reimbursements> pending = new ArrayList<Reimbursements>();
		ResultSet reimb = null;

		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT * FROM GET_PENDING_REIMBS WHERE U_ID_AUTHOR = ?";
			cs = conn.prepareStatement(sql);
			cs.setInt(1, id);
			reimb = cs.executeQuery();

			while (reimb.next()) {
				r_id = reimb.getInt("R_ID");
				r_amount = reimb.getDouble("R_AMOUNT");
				r_description = reimb.getString("R_DESCRIPTION");
				r_submitted = reimb.getString("R_SUBMITTED");
				r_type = reimb.getInt("RT_TYPE");
				u_id_author = reimb.getInt("U_ID_AUTHOR");

				Reimbursements c = new Reimbursements(0, r_amount, r_description, r_submitted, "", r_type,
						1);
				pending.add(c);

			}
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pending;
	}

	@Override
	public List<Reimbursements> viewResolved(int id) throws IOException, SQLException {
		PreparedStatement cs = null;
		int r_id;
		double r_amount;
		String r_description;
		String r_submitted;
		int r_type;
		int u_id_author, status;
		List<Reimbursements> pending = new ArrayList<Reimbursements>();
		ResultSet reimb = null;
		String r_resolved;
		
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR = ? AND RT_STATUS !=1";
			cs = conn.prepareStatement(sql);
			cs.setInt(1, id);
			reimb = cs.executeQuery();

			while (reimb.next()) {
				r_id = reimb.getInt("R_ID");
				r_amount = reimb.getDouble("R_AMOUNT");
				r_description = reimb.getString("R_DESCRIPTION");
				r_submitted = reimb.getString("R_SUBMITTED");
				r_resolved = reimb.getString("R_RESOLVED");
				r_type = reimb.getInt("RT_TYPE");
				u_id_author = reimb.getInt("U_ID_AUTHOR");
				status = reimb.getInt("RT_STATUS");
				

				Reimbursements c = new Reimbursements(r_id, r_amount, r_description, r_submitted, r_resolved,
						r_type, status);
				pending.add(c);

			}
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pending;
	}
}
