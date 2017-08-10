package com.revature.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.revature.domain.Reimbursement;
import com.revature.domain.User;
import com.revature.exception.InvalidLoginException;
import com.revature.util.ConnectionUtil;

public class ERSDAOImpl implements ERSDAO {

	@Override
	public User employeeLogin(String username, String password) throws InvalidLoginException {
		User u;

		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT * FROM ERS_USERS WHERE U_USERNAME = ? AND U_PASSWORD = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.toString());
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
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
	public boolean updateEmployee(User u) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE ERS_USERS SET U_PASSWORD = ?, U_FIRSTNAME = ?, U_LASTNAME = ?, U_EMAIL = ? WHERE U_ID = ?");
			pstmt.setString(1, u.getPassword());
			pstmt.setString(2, u.getFirstname());
			pstmt.setString(3, u.getLastname());
			pstmt.setString(4, u.getEmail());
			pstmt.setInt(5, u.getId());
			return pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Reimbursement> viewUserReimb(User u, int type) {
		ArrayList<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID = ? AND RT_STATUS = ?");
			pstmt.setInt(1, u.getId());
			pstmt.setInt(2, type);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getDate(4), u,
						rs.getInt(6), rs.getInt(7)));
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
	public boolean submitReimbursement(Reimbursement re) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ERS_REIMBURSEMENTS VALUES (?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, re.getId());
			pstmt.setDouble(2, re.getAmount());
			pstmt.setString(3, re.getDescription());
			pstmt.setDate(4, re.getSubmitted());
			pstmt.setInt(5, re.getAuthor().getId());
			pstmt.setInt(6, re.getType());
			pstmt.setInt(7, re.getStatus());
			return pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Reimbursement> viewAllReimb(int type) {
		ArrayList<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS WHERE RT_STATUS = ?");
			pstmt.setInt(1, type);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getDate(4), new User(rs.getInt(5)),
						rs.getInt(6), rs.getInt(7)));
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
	public ArrayList<User> viewAllEmployees() {
		ArrayList<User> users = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ERS_USERS");

			while (rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7)));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return users;
	}

	@Override
	public User viewEmployee(int uid) {
		User u = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE U_ID = ?");
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));	
			return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return u;
	}

	@Override
	public void updateReimbursement() {
		// TODO Auto-generated method stub
		
	}

}
