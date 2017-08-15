package com.revature.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

import com.revature.domain.Reimbursement;
import com.revature.domain.User;
import com.revature.exception.InvalidLoginException;
import com.revature.util.ConnectionUtil;

public class ERSDAOImpl implements ERSDAO {

	@Override
	public User empLogin(String username, String password) throws InvalidLoginException {
		User u;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM ERS_USERS WHERE U_USERNAME = ? AND U_PASSWORD = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				u = newUserFromRS(rs);
				return u;
			} else
				throw new InvalidLoginException("L2type noob");

		} catch (InvalidLoginException e) {
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new User(0);
	}

	@Override
	public boolean updateEmp(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ERS_USERS SET U_PASSWORD = ?, U_FIRSTNAME = ?, U_LASTNAME = ?, U_EMAIL = ? WHERE U_ID = ?");
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
	public Reimbursement[] viewUserReimb(User u, int type) {
		ArrayList<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR = ? AND RT_STATUS = ?");
			pstmt.setInt(1, u.getId());
			pstmt.setInt(2, type);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				reimbursements.add(newReimbFromRS(rs));
			}
			return reimbursements.toArray(new Reimbursement[reimbursements.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return reimbursements.toArray(new Reimbursement[reimbursements.size()]);
	}

	@Override
	public boolean submitReimb(Reimbursement re, InputStream fileContent) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			Blob blob = conn.createBlob();
			OutputStream out = blob.setBinaryStream(0);
			
			IOUtils.copy(fileContent, out);
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO ERS_REIMBURSEMENTS(R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, U_ID_AUTHOR, RT_TYPE, RT_STATUS) VALUES (?, ?, ?, ?, ?, ?, ?)");
			pstmt.setDouble(1, re.getAmount());
			pstmt.setString(2, re.getDescription());
			pstmt.setBlob(3, blob);
			pstmt.setDate(4, re.getSubmitted());
			pstmt.setInt(5, re.getAuthor());
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
	public Reimbursement[] viewAllReimb(int type) {
		ArrayList<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ERS_REIMBURSEMENTS WHERE RT_STATUS = ?");
			pstmt.setInt(1, type);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				reimbursements.add(newReimbFromRS(rs));
			}
			return reimbursements.toArray(new Reimbursement[reimbursements.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return reimbursements.toArray(new Reimbursement[reimbursements.size()]);
	}

	@Override
	public ArrayList<User> viewAllEmps() {
		ArrayList<User> users = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ERS_USERS");

			while (rs.next()) {
				users.add(newUserFromRS(rs));
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
	public User viewEmp(int uid) {
		User u = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE U_ID = ?");
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				u = newUserFromRS(rs);
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
	public boolean updateReimb(Reimbursement r) { // JSUT ALLOW MANAGERS TO APPROVE OR DENY A REIMB
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ERS_REIMBURSEMENTS SET R_RESOLVED = ?, U_ID_RESOLVER = ?, RT_STATUS = ? WHERE R_ID = ?");
			pstmt.setDate(1, r.getResolved());
			pstmt.setInt(2, r.getResolver());
			pstmt.setInt(3, r.getStatus());
			pstmt.setInt(4, r.getId());
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

	private User newUserFromRS(ResultSet rs) throws SQLException {
		return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getInt(7));
	}

	private Reimbursement newReimbFromRS(ResultSet rs) throws SQLException, IOException {
		byte[] blobval = Base64.getEncoder().encode(IOUtils.toByteArray(rs.getBlob(4).getBinaryStream()));
		return new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), blobval, rs.getDate(5),
				rs.getDate(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
	}

}
