package com.revature.team201.reimbursement.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.team201.reimbursement.users.Employee;
import com.revature.team201.reimbursement.users.Reimbursement;
import com.revature.team201.reimbursement.users.Status;
import com.revature.team201.reimbursement.util.ConnectionUtil;

public class ManagerDAOImpl implements ManagerDAO{
	
	public void updateStatusOnReimbursement(Integer reimbursementId, Status status) {

		PreparedStatement ps = null;
		try (Connection connection = ConnectionUtil.getConnection()){
			// Find the statusId number that is in the db that corresponds to status.
			Integer statusId = findStatusId(status);
				
			// Only updates the status variable in the db
			String statement = "UPDATE reimbursements SET rt_status = ? WHERE r_id = ?";
			
			ps = connection.prepareStatement(statement);
			ps.setInt(1, statusId);
			ps.setInt(2, reimbursementId);
			
			ps.executeUpdate();
		} catch (SQLException | IOException e) {
			if (ps != null) {
				try { ps.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			}
		}
	}

	public ArrayList<Employee> getAllEmployees() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Employee> employees = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil.getConnection()){
			String statement = "SELECT * FROM user_roles INNER JOIN uusers " 
					        + "ON uusers.ur_id = user_roles.ur_id " 
					        + "WHERE user_roles.ur_role = 'EMPLOYEE'";
			
			ps = connection.prepareStatement(statement);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer userId = rs.getInt("u_id");
				String userName = rs.getString("u_username");
				String userFirstName = rs.getString("u_firstname");
				String userLastName = rs.getString("u_lastname");
				String userEmail = rs.getString("u_email");
				
				Employee employee = new Employee(userId, userName, userFirstName, userLastName, userEmail);
				employees.add(employee);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close(); } catch (SQLException ex) { ex.printStackTrace(); };
			}
			if (rs != null) {
				try { ps.close(); } catch (SQLException ex) { ex.printStackTrace();};
			}
		}
		return employees;
	}
	
	public ArrayList<Reimbursement> getAllPendingReimbursements() {
		String sql = "SELECT * FROM reimbursement_status "
				 + "INNER JOIN reimbursements ON reimbursements.rt_status = reimbursement_status.rs_id "
				 + "WHERE reimbursement_status.rs_status = 'PENDING'";
		
		return getReimbursements(sql);
	}
	
	public ArrayList<Reimbursement> getAllResolvedReimbursements() {
		String sql = "SELECT * FROM reimbursement_status "
				 + "INNER JOIN reimbursements ON reimbursements.rt_status = reimbursement_status.rs_id "
				 + "WHERE reimbursement_status.rs_status = 'APPROVED' OR reimbursement_status.rs_status = 'DENIED'";
		
		return getReimbursements(sql);
	}
	
	public ArrayList<Reimbursement> getEmployeeReimbursements(Integer empId) {
		
		
		String sql = "SELECT * FROM reimbursement_status "
				 + "INNER JOIN reimbursements ON reimbursements.rt_status = reimbursement_status.rs_id";
		
		return getReimbursements(sql);
	}

	private ArrayList<Reimbursement> getReimbursements(String sql) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Reimbursement> reimbursements = new ArrayList<>();
		
		
		try (Connection connection = ConnectionUtil.getConnection()){			
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery(); 
			
			
			while (rs.next()) {
				Integer rId = rs.getInt("r_id");
				Double amount = rs.getDouble("r_amount");
				String description = rs.getString("r_description");
				Timestamp dateSubmitted = rs.getTimestamp("r_submitted");
				Timestamp dateResolved = rs.getTimestamp("r_resolved");
				Integer authorId = rs.getInt("u_id_author");
				Integer resolverId = rs.getInt("u_id_resolver");
				// Type type = Status.valueOf(rs.getString("rt_type")); // needs a second inner join
				String type = rs.getString("u_id_resolver");
				Status status = Status.valueOf(rs.getString("rs_status"));
				boolean hasReceipt = false;
				
				Reimbursement reimbursement = new Reimbursement(rId, amount, description, 
														null, dateSubmitted, dateResolved, 
														authorId, resolverId, type, status, 
														hasReceipt);
				
				reimbursements.add(reimbursement);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try { rs.close(); } catch (SQLException ex) { ex.printStackTrace();}
			}
			if (rs != null) {
				try { rs.close(); } catch (SQLException ex) { ex.printStackTrace();}
			}
		}
		
		return reimbursements;
	}
	
	private Integer findStatusId(Status status) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer statusId = null;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String statement = "SELECT * FROM reimbursement_status WHERE rs_status = ?";
			
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
	

}
