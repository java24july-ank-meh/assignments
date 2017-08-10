package com.revature.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.revature.domain.Reimbursement;
import com.revature.domain.User;
import com.revature.exception.InvalidLoginException;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public User employeeLogin(String username, String password) throws InvalidLoginException {
		User u;

		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT * FROM ERS_USERS LEFT JOIN ERS_USER_ROLES ON ERS_USERS.UR_ID = ERS_USER_ROLES.UR_ID WHERE U_USERNAME = ? AND U_PASSWORD = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.toString());
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(9));
				return u;
			} else
				throw new InvalidLoginException("Whoa nelly. Wrong username and password combo, pal");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateEmployee(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Reimbursement> viewReimbursements(User u, int type) {
		ArrayList<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID = ? AND RT_STATUS = ?");
			pstmt.setInt(1, u.getId());
			pstmt.setInt(2, type);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getDate(4), u,
						rs.getString(6), rs.getString(7)));
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return reimbursements;
	}

	@Override
	public void submitReimbursement() {
		// TODO Auto-generated method stub

	}

}
