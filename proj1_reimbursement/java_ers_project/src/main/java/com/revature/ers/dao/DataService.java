package com.revature.ers.dao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.revature.ers.beans.*;

public class DataService implements DAO{

	@Override
	public User verifyUser(String username, String password) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ERS_USER WHERE u_username = ? AND u_password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("u_id");
				String firstname = rs.getString("u_firstname");
				String lastname = rs.getString("u_lastname");
				String email = rs.getString("u_email");
				UserRole role = UserRole.valueOf(rs.getInt("ur_id"));
				return new User(id,username,firstname,lastname,email,role);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUser(String username) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ERS_USER WHERE u_username = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("u_id");
				String firstname = rs.getString("u_firstname");
				String lastname = rs.getString("u_lastname");
				String email = rs.getString("u_email");
				UserRole role = UserRole.valueOf(rs.getInt("ur_id"));
				return new User(id,username,firstname,lastname,email,role);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql="UPDATE ERS_USER SET U_USERNAME=?, U_FIRSTNAME=?,U_LASTNAME=?,U_EMAIL=? WHERE U_ID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getFirstname());
			pstmt.setString(3,user.getLastname());
			pstmt.setString(4, user.getEmail());
			pstmt.setInt(5, user.getId());
            int rowsAff=pstmt.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
            if(rowsAff>0)
            	return true;
            else
            	return false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean denyRequest(int req_id, int resolver_id) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "UPDATE ERS_REIMBURSEMENT "
					+ "SET r_resolved = CURRENT_TIMESTAMP, u_id_resolver = ?, rs_status = 2 WHERE r_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, resolver_id);
			pstmt.setInt(2, req_id);
			int rowCount = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			if(rowCount > 0) return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean approveRequest(int req_id, int resolver_id) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "UPDATE ERS_REIMBURSEMENT "
					+ "SET r_resolved = CURRENT_TIMESTAMP, u_id_resolver = ?, rs_status = 3 WHERE r_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, resolver_id);
			pstmt.setInt(2, req_id);
			int rowCount = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			if(rowCount > 0) return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Request> getPendingRequests(int employee_id) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT r_id,r_amount,r_description,r_receipt,r_submitted,u_id," + 
					"u_username,rt_type,rs_status FROM ERS_REIMBURSEMENT " + 
					"INNER JOIN ERS_USER ON u_id_author = u_id " +
					"WHERE u_id = ? AND rs_status = 1 ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employee_id);
			ResultSet rs = pstmt.executeQuery();
			List<Request> requests = new ArrayList<Request>();
			
			while(rs.next()) {
				int r_id = rs.getInt("r_id");
				double r_amount = rs.getDouble("r_amount");
				String r_description = rs.getString("r_description");
				boolean r_receipt = rs.getBlob("r_receipt") != null;
				Date r_submitted = rs.getDate("r_submitted");
				Date r_resolved = null;
				int u_id_author = rs.getInt("u_id");
				String author_name = rs.getString("u_username");
				int u_id_resolver = -1;
				String resolver_name = null;
				RequestType rt_type = RequestType.valueOf(rs.getInt("rt_type"));
				RequestStatus rs_status = RequestStatus.valueOf(rs.getInt("rs_status"));
				requests.add(new Request(r_id,r_amount,r_description,r_receipt,r_submitted,
						r_resolved,u_id_author,author_name,u_id_resolver,resolver_name,rt_type,rs_status));
			}
			
			return requests;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Request> getResolvedRequests(int employee_id) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT r_id,r_amount,r_description,r_receipt,r_submitted,r_resolved,U1.u_id," + 
					"U1.u_username,U2.u_id AS manager_id,U2.u_username AS manager_name,rt_type,rs_status " + 
					"FROM ERS_REIMBURSEMENT " + 
					"INNER JOIN ERS_USER U1 ON u_id_author = U1.u_id " + 
					"INNER JOIN ERS_USER U2 ON u_id_resolver = U2.u_id " + 
					"WHERE U1.u_id = ? AND (rs_status = 2 OR rs_status = 3)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employee_id);
			ResultSet rs = pstmt.executeQuery();
			List<Request> requests = new ArrayList<Request>();
			while(rs.next()) {
				int r_id = rs.getInt("r_id");
				double r_amount = rs.getDouble("r_amount");
				String r_description = rs.getString("r_description");
				boolean r_receipt = rs.getBlob("r_receipt") != null;
				Date r_submitted = rs.getDate("r_submitted");
				Date r_resolved = rs.getDate("r_resolved");
				int u_id_author = rs.getInt("u_id");
				String author_name = rs.getString("u_username");
				int u_id_resolver = rs.getInt("manager_id");
				String resolver_name = rs.getString("manager_name");
				RequestType rt_type = RequestType.valueOf(rs.getInt("rt_type"));
				RequestStatus rs_status = RequestStatus.valueOf(rs.getInt("rs_status"));
				requests.add(new Request(r_id,r_amount,r_description,r_receipt,r_submitted,
						r_resolved,u_id_author,author_name,u_id_resolver,resolver_name,rt_type,rs_status));
			}
			return requests;
		} catch(Exception e) {
		}
		return null;
	}

	@Override
	public List<Request> getPendingRequests() {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT r_id,r_amount,r_description,r_receipt,r_submitted,u_id," + 
					"u_username,rt_type,rs_status FROM ERS_REIMBURSEMENT " + 
					"INNER JOIN ERS_USER ON u_id_author = u_id " + 
					"WHERE rs_status = 1";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<Request> requests = new ArrayList<Request>();
			while(rs.next()) {
				int r_id = rs.getInt("r_id");
				double r_amount = rs.getDouble("r_amount");
				String r_description = rs.getString("r_description");
				boolean r_receipt = rs.getBlob("r_receipt") != null;
				Date r_submitted = rs.getDate("r_submitted");
				Date r_resolved = null;
				int u_id_author = rs.getInt("u_id");
				String author_name = rs.getString("u_username");
				int u_id_resolver = -1;
				String resolver_name = null;
				RequestType rt_type = RequestType.valueOf(rs.getInt("rt_type"));
				RequestStatus rs_status = RequestStatus.valueOf(rs.getInt("rs_status"));
				requests.add(new Request(r_id,r_amount,r_description,r_receipt,r_submitted,
						r_resolved,u_id_author,author_name,u_id_resolver,resolver_name,rt_type,rs_status));
			}
			return requests;
		} catch(Exception e) {
		}
		return null;
	}

	@Override
	public List<Request> getResolvedRequests() {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT r_id,r_amount,r_description,r_receipt,r_submitted,r_resolved,U1.u_id," + 
					"U1.u_username,U2.u_id AS manager_id,U2.u_username AS manager_name,rt_type,rs_status " + 
					"FROM ERS_REIMBURSEMENT " + 
					"INNER JOIN ERS_USER U1 ON u_id_author = U1.u_id " + 
					"INNER JOIN ERS_USER U2 ON u_id_resolver = U2.u_id " + 
					"WHERE rs_status = 2 OR rs_status = 3";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<Request> requests = new ArrayList<Request>();
			while(rs.next()) {
				int r_id = rs.getInt("r_id");
				double r_amount = rs.getDouble("r_amount");
				String r_description = rs.getString("r_description");
				boolean r_receipt = rs.getBlob("r_receipt") != null;
				Date r_submitted = rs.getDate("r_submitted");
				Date r_resolved = rs.getDate("r_resolved");
				int u_id_author = rs.getInt("u_id");
				String author_name = rs.getString("u_username");
				int u_id_resolver = rs.getInt("manager_id");
				String resolver_name = rs.getString("manager_name");
				RequestType rt_type = RequestType.valueOf(rs.getInt("rt_type"));
				RequestStatus rs_status = RequestStatus.valueOf(rs.getInt("rs_status"));
				requests.add(new Request(r_id,r_amount,r_description,r_receipt,r_submitted,
						r_resolved,u_id_author,author_name,u_id_resolver,resolver_name,rt_type,rs_status));
			}
			return requests;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getEmployees() {
		PreparedStatement pstmt = null;
		ArrayList<User> arr = new ArrayList<User>();
		try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ERS_USER WHERE UR_ID=1";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
            	int id=rs.getInt("U_ID");
            	String username=rs.getString("U_USERNAME");
            	String firstname=rs.getString("U_FIRSTNAME");
            	String lastname=rs.getString("U_LASTNAME");
            	String email=rs.getString("U_EMAIL");
            	//int role=rs.getInt("UR_ID");
            	
            	User u = new User(id, username, firstname, lastname, email, UserRole.EMPLYEE);
            	arr.add(u);
            }
            return arr;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createUser(String username, String password, String firstname, 
			String lastname, String email, UserRole role) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ERS_USER (u_username,u_password,u_firstname,u_lastname,u_email,ur_id) VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, firstname);
			pstmt.setString(4, lastname);
			pstmt.setString(5, email);
			pstmt.setInt(6, role.getId());
			int rowCount = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			if(rowCount > 0) return true;
		} catch(Exception e) {
		}
		return false;
	}

	@Override
	public boolean createRequest(double amount,String description, InputStream receipt_image, int user_id, RequestType type) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ERS_REIMBURSEMENT "
					+ "(r_amount, r_description, r_receipt, r_submitted,u_id_author,rt_type,rs_status) "
					+ "VALUES (?,?,?,CURRENT_TIMESTAMP,?,?,1) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, amount);
			pstmt.setString(2, description);
			pstmt.setBlob(3, receipt_image);
			pstmt.setInt(4, user_id);
			pstmt.setInt(5, type.getId());
			int rowCount = pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			if(rowCount > 0) return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void getReceipt(int user_id, int receipt_id, OutputStream out) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT r_receipt FROM ERS_REIMBURSEMENT WHERE r_id = ? AND u_id_author = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, receipt_id);
			pstmt.setInt(2, user_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				InputStream receipt = rs.getBinaryStream("r_receipt");
				if(receipt != null) {
					byte[] buf = new byte[1024];
					while (receipt.read(buf) > 0) {
						out.write(buf);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getReceipt(int receipt_id, OutputStream out) {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT r_receipt FROM ERS_REIMBURSEMENT WHERE r_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, receipt_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				InputStream receipt = rs.getBinaryStream("r_receipt");
				if(receipt != null) {
					byte[] buf = new byte[1024];
					while (receipt.read(buf) > 0) {
						out.write(buf);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
