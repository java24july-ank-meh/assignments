package com.revature.team201.reimbursement.db;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.revature.team201.reimbursement.users.Employee;
import com.revature.team201.reimbursement.users.Reimbursement;
import com.revature.team201.reimbursement.users.Status;
import com.revature.team201.reimbursement.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO{

	public Employee getEmployeeInformation(Integer employeeId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM UUSERS WHERE U_ID = ?";
		Employee user = null;
		try (Connection connection = ConnectionUtil.getConnection()){
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// USER CREDENTIALS ACCEPTED. CREATING USER
				Integer userId = rs.getInt("U_ID");
				String userName = rs.getString("U_USERNAME");
				String firstName = rs.getString("U_FIRSTNAME");
				String lastName = rs.getString("U_LASTNAME");
				String eMail =  rs.getString("U_EMAIL");
				user = new Employee(userId, userName, firstName, lastName, eMail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		return user;
	}

	public void updateEmployeeInformation(Employee employee, String password) {
		PreparedStatement pstmt = null;
		int rs = 0;
		String sql = "UPDATE UUSERS " + 
				"SET U_USERNAME = ?, U_FIRSTNAME = ?, U_LASTNAME = ?, U_EMAIL = ?, U_PASSWORD = ? " + 
				"WHERE U_ID = ?";
		try (Connection connection = ConnectionUtil.getConnection()){
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, employee.getUsername());
			pstmt.setString(2, employee.getFirstName());
			pstmt.setString(3, employee.getLastName());
			pstmt.setString(4, employee.geteMail());
			pstmt.setString(5, password);
			pstmt.setInt(6, employee.getUserId());
			rs = pstmt.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
		}
	}

	public void submitReimbursement(Reimbursement reimbursement) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO REIMBURSEMENTS (R_AMOUNT, R_DESCRIPTION, "
				+ "R_RECEIPT, R_SUBMITTED, U_ID_AUTHOR, RT_STATUS, RT_TYPE) VALUES(?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = ConnectionUtil.getConnection()){
			ByteArrayInputStream bais = null;
			BufferedImage buffered = reimbursement.getReceipt();
			if(buffered != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(buffered, "jpg", baos);
				bais = new ByteArrayInputStream(baos.toByteArray());
			}
			pstmt = connection.prepareStatement(sql);
			pstmt.setDouble(1, reimbursement.getAmount());
			pstmt.setString(2, reimbursement.getDescription());
			pstmt.setBlob(3, bais);
			pstmt.setTimestamp(4, reimbursement.getDateSubmitted());
			pstmt.setInt(5, reimbursement.getAuthor());
			pstmt.setInt(6, findStatusId(Status.PENDING));
			pstmt.setInt(7, findTypeId(reimbursement.getType()));
			
			rs = pstmt.executeQuery();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
	}

	public ArrayList<Reimbursement> getPendingReimbursements(Integer userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT_STATUS "
				+ "INNER JOIN REIMBURSEMENTS ON REIMBURSEMENTS.RT_STATUS = REIMBURSEMENT_STATUS.RS_ID "
				+ "WHERE REIMBURSEMENT_STATUS.RS_STATUS = 'PENDING' AND U_ID_AUTHOR = ?";
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		try (Connection connection = ConnectionUtil.getConnection()){
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("R_ID");
				Double amt = rs.getDouble("R_AMOUNT");
				String desc = rs.getString("R_DESCRIPTION");
				Timestamp submitted = rs.getTimestamp("R_SUBMITTED");
				Timestamp resolved = rs.getTimestamp("R_RESOLVED");
				Integer author = rs.getInt("U_ID_AUTHOR");
				Integer resolver = rs.getInt("U_ID_RESOLVER");
				String type = rs.getString("RT_TYPE");
				boolean hasreceipt = rs.getBlob("R_RECEIPT") == null? false : true;
				Reimbursement r = new Reimbursement(id, amt, desc, null, submitted, resolved,
						author, resolver, type, Status.PENDING, hasreceipt);
				list.add(r);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		return list;
	}

	public ArrayList<Reimbursement> getResolvedReimbursements(Integer userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT_STATUS "
				+ "INNER JOIN REIMBURSEMENTS ON REIMBURSEMENTS.RT_STATUS = REIMBURSEMENT_STATUS.RS_ID "
				+ "WHERE REIMBURSEMENT_STATUS.RS_STATUS != 'PENDING' AND U_ID_AUTHOR = ?";
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		try (Connection connection = ConnectionUtil.getConnection()){
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("R_ID");
				Double amt = rs.getDouble("R_AMOUNT");
				String desc = rs.getString("R_DESCRIPTION");
				Timestamp submitted = rs.getTimestamp("R_SUBMITTED");
				Timestamp resolved = rs.getTimestamp("R_RESOLVED");
				Integer author = rs.getInt("U_ID_AUTHOR");
				Integer resolver = rs.getInt("U_ID_RESOLVER");
				String type = rs.getString("RT_TYPE");
				Status status = Status.valueOf(rs.getString("RS_STATUS"));
				boolean hasreceipt = rs.getBlob("R_RECEIPT") == null? false : true;
				Reimbursement r = new Reimbursement(id, amt, desc, null, submitted, resolved,
						author, resolver, type, status, hasreceipt);
				list.add(r);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		return list;
	}
	
	private Integer findStatusId(Status status) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer statusId = null;
		
		try (Connection connection = ConnectionUtil.getConnection()){
			String statement = "SELECT * FROM REIMBURSEMENT_STATUS WHERE rs_status = ?";
			
			ps = connection.prepareStatement(statement);
			ps.setString(1, status.name());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				statusId = rs.getInt("rs_id");
			}	
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return statusId;
	}
	private Integer findTypeId(String type) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer statusId = null;
		try (Connection connection = ConnectionUtil.getConnection()){
			String statement = "SELECT * FROM REIMBURSEMENT_TYPE WHERE RT_TYPE = ?";
			ps = connection.prepareStatement(statement);
			ps.setString(1, type);
			
			rs = ps.executeQuery();
			
			if(rs.next())
				statusId = rs.getInt("RT_ID");
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return statusId;
	}
}
