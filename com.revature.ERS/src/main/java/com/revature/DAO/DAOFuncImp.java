package com.revature.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.revature.ConnectionUtil.ConnectionUtil;
import com.revature.ERS.Employee;
import com.revature.ERS.Reimbursement;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
public class DAOFuncImp implements DAOFunctions{

	@Override
	public List<String> readEmployee(int id) {

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<String> employeeInfo = new ArrayList<String>();
		try(Connection conn =  ConnectionUtil.getConnection()){
			
			final  String sql = "SELECT * FROM ers_users WHERE u_id = ?";
			final String sql2 = "SELECT ur_role FROM ers_user_roles WHERE ur_id = ?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt.setInt(1, id);
			pstmt2.setInt(1, id);
			
			rs = pstmt.executeQuery();
			rs2 = pstmt2.executeQuery();
			
			while(rs.next()) {
				String u_username = rs.getString("u_username");
				String u_firstname = rs.getString("u_firstname");
				String u_lastname = rs.getString("u_lastname");
				String u_email = rs.getString("u_email");
				
				employeeInfo.add(u_username);
				employeeInfo.add(u_firstname);
				employeeInfo.add(u_lastname);
				employeeInfo.add(u_email);
			}
			
			while(rs2.next()) {
				String ur_role = rs.getString("ur_role");
				employeeInfo.add(ur_role);
			}
			
			return employeeInfo;
			
		}catch(SQLException se) {
			if(pstmt != null) {
				try{
					pstmt.close();
					pstmt2.close();
				}catch(SQLException seq) {
					seq.printStackTrace();
				}
			}
			
			if(rs != null) {
				try {
					rs.close();
					rs2.close();
				}catch(SQLException seq) {
					seq.printStackTrace();
				}
			}
			
			return null;
		}
		
	}

	@Override
	public void createEmployee(Employee e) {//this method inserts a user and a user role into the database.
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			String sql = "INSERT INTO ers_users (u_username, u_password, u_firstname, u_lastname, u_email) " +
					"VALUES(?, ?, ?, ?, ?)";
			String sql2 = "INSERT INTO ers_user_roles (ur_role) VALUES(?)";
			e.setU_password(e.generateRandomPass());
			
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt.setString(1, e.getU_username());
			pstmt.setString(2, e.getU_password());
			pstmt.setString(3, e.getU_firstname());
			pstmt.setString(4, e.getU_lastname());
			pstmt.setString(5, e.getU_email());
			
			pstmt2.setString(1, e.getUr_role());
			
			
			pstmt.executeQuery();
			pstmt2.executeQuery();
			
			pstmt.close();
			pstmt2.close();
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
	}

	@Override
	public void deleteEmployee(int id) {

		PreparedStatement pstmt = null, pstmt2 = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "DELETE FROM ers_users WHERE u_id = ?";
			String sql2 = "DELETE FROM ers_user_roles WHERE ur_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt.setInt(1, id);
			pstmt2.setInt(1, id);
			
			int nDelete = pstmt.executeUpdate(sql);
			int nDelete2 = pstmt2.executeUpdate(sql2);
			
			System.out.println("Rows affected: " + nDelete + " and " + nDelete2);
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
	}

	@Override
	public void updateEmployee(Employee e, int id) {
		PreparedStatement pstmt = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "UPDATE ers_users SET " +
					"u_username  = ?, " +
					"u_password = ?, " +
					"u_firstname = ?, " +
					"u_lastname = ?, " +
					"u_email = ? " +
					"WHERE u_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getU_username());
			pstmt.setString(2, e.getU_password());
			pstmt.setString(3, e.getU_firstname());
			pstmt.setString(4, e.getU_lastname());
			pstmt.setString(5, e.getU_email());
			pstmt.setInt(5, id);
			
			pstmt.executeQuery();
			
			pstmt.close();
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
	}

	@Override
	public int userLogin(String user, String pass) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int u_id = 0;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT u_id FROM ers_users WHERE u_username = ? AND u_password = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				u_id = rs.getInt("u_id");
				
			}
			
			pstmt.close();
			rs.close();
			
			return u_id;
			
		}catch(SQLException se) {
			if(pstmt != null) {
				try{
					pstmt.close();
				}catch(SQLException seq) {
					seq.printStackTrace();
				}
			}
			
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException seq) {
					seq.printStackTrace();
				}
			}
		}
		
		return -1;
	}

	@Override
	public List<Employee> readAllEmp() {
		PreparedStatement pstmt = null;
		List<Employee> employee = new ArrayList<>();
		
		
		String u_username, u_firstname, u_lastname, u_email;
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM ers_user";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				u_username = rs.getString("u_username");
				u_firstname = rs.getString("u_firstname");
				u_lastname = rs.getString("u_lastname");
				u_email = rs.getString("u_email");
				
				Employee e = new Employee();
				e.setU_username(u_username);
				e.setU_firstname(u_firstname);
				e.setU_lastname(u_lastname);
				e.setU_email(u_email);
				
				employee.add(e);
			}
			rs.close();
		}catch(SQLException se) {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException seq) {
					seq.printStackTrace();
				}
			}
		}
		
		return employee;
	}

	@Override
	public void saveReimbursementWithImage(int id, Reimbursement r, byte[] blobByte) {//be sure to pass id resolver as 0 as it is not default on database
		
		PreparedStatement pstmt = null;
		Blob blob = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO ers_reimbursements " +
					"(r_amount, r_description, r_receipt, r_submitted, r_resolved, u_id_author, u_id_resolver, rt_type, rs_status) " +
					" VALUES( ?, ?, ?, ?, ?, ?, 0, ?, 0)";
			
			String sql2 = "INSERT INTO ers_reimbursement_status (rs_status) VALUES('pending')";
			
			String sql3 = "INSERT INTO ers_reimbursement_type (rt_type) VALUES ('user')";
			
			blob = conn.createBlob();
			blob.setBytes(1, blobByte);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, r.getR_amount());
			pstmt.setString(2, r.getR_description());
			pstmt.setBlob(3, blob);
			pstmt.setDate(4, r.getR_submited());
			pstmt.setDate(5, r.getR_resolved());
			pstmt.setInt(6, r.getU_id_author());
			pstmt.setInt(7, r.getRt_type());
			
			pstmt.executeQuery();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException seq) {
					seq.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Reimbursement> readReimbursement() {
		
		Statement stmt = null;
		ResultSet rs = null;
		List<Reimbursement> reList = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM ers_reimbursements";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				
				r.setR_id(rs.getInt("r_id"));
				r.setR_amount(rs.getDouble("r_amount"));
				r.setR_description(rs.getString("r_description"));
				r.setR_submited(rs.getDate("r_submited"));
				r.setR_resolved(rs.getDate("r_resolved"));
				r.setU_id_author(rs.getInt("u_id_author"));
				r.setU_id_resolver(rs.getInt("u_id_resolver"));
				r.setRt_type(rs.getInt("rt_type"));
				r.setRs_status(rs.getInt("rs_status"));
				
				reList.add(r);
			}
			
			
			rs.close();
			
			return reList;
		}catch(SQLException se) {
			try {
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reList;
	}


	@Override
	public BufferedImage readBlob(int r_id, byte[] blob) {
		try {
			
			ByteArrayInputStream bais = new ByteArrayInputStream(blob);
			
			return ImageIO.read(bais);
		}catch(Exception io) {
			io.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void saveReimbursement(int id, Reimbursement r) {//this will create a reimbursement linked to the type and status
		PreparedStatement pstmt = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			
			String sql = "INSERT INTO ers_reimbursement(r_amount, r_description, r_submitted, r_resolved, u_id_author, u_id_resolver, rt_type, rs_status) " +
					" VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			String sql2 = "INSERT INTO ers_reimbursement_status (rs_status) VALUES('pending')";
			
			String sql3 = "INSERT INTO ers_reimbursement_type (rt_type) VALUES ('user')";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDouble(1, r.getR_amount());
			pstmt.setString(2, r.getR_description());
			pstmt.setDate(3, r.getR_submited());
			pstmt.setDate(4, r.getR_resolved());
			pstmt.setInt(5, id);
			pstmt.setInt(6, r.getU_id_resolver());
			pstmt.setInt(7, r.getRt_type());
			pstmt.setInt(8, r.getRs_status());
			
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.executeUpdate();
			
			
		}catch(SQLException se) {
			if(pstmt != null) {
				try{
					pstmt.close();
				}catch(SQLException seq) {
					
				}
			}
		}
		
	}

	@Override
	public void reimbursementStatus(int r_id, int rs_id, String status) {
		
		PreparedStatement pstmt = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "UPDATE ers_reimbursement_status "
					+ "SET rs_status = ? WHERE rs_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, rs_id);
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException seq) {
					seq.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public List<Reimbursement> readPendingReimbursements(int u_id, int r_id) {
		
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Reimbursement> reimList = new ArrayList<>();
		Reimbursement r = new Reimbursement();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT rs_id WHERE rs_status = 'pending' OR rs_status = 'Pending' ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int rs_id = rs.getInt("rs_id");
				
				sql = "SELECT * FROM ers_reimbursements WHERE rs_status = " + rs_id;
				
				stmt = conn.createStatement();
				rs2 = stmt.executeQuery(sql);
				
				while(rs2.next()) {
					r.setR_amount(rs2.getDouble("r_amount"));
					r.setR_description(rs2.getString("r_description"));
					r.setR_submited(rs2.getDate("r_submitted"));
					r.setR_resolved(rs2.getDate("r_resolved"));
					r.setU_id_author(rs2.getInt("u_id_author"));
					r.setU_id_resolver(rs2.getInt("u_id_resolver"));
					r.setRt_type(rs2.getInt("rt_type"));
					r.setRs_status(rs2.getInt("rs_status"));
					
					reimList.add(r);
				}
				
			}
			
			
			stmt.close();
			rs.close();
			rs2.close();
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return reimList;
	}

	@Override
	public List<Reimbursement> readResolvedReimbursements(int u_id, int r_id) {
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Reimbursement> reimList = new ArrayList<>();
		Reimbursement r = new Reimbursement();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT rs_id WHERE rs_status = 'approved' OR rs_status = 'Approved' ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int rs_id = rs.getInt("rs_id");
				
				sql = "SELECT * FROM ers_reimbursements WHERE rs_status = " + rs_id;
				
				stmt = conn.createStatement();
				rs2 = stmt.executeQuery(sql);
				
				while(rs2.next()) {
					r.setR_amount(rs2.getDouble("r_amount"));
					r.setR_description(rs2.getString("r_description"));
					r.setR_submited(rs2.getDate("r_submitted"));
					r.setR_resolved(rs2.getDate("r_resolved"));
					r.setU_id_author(rs2.getInt("u_id_author"));
					r.setU_id_resolver(rs2.getInt("u_id_resolver"));
					r.setRt_type(rs2.getInt("rt_type"));
					r.setRs_status(rs2.getInt("rs_status"));
					
					reimList.add(r);
				}
				
			}
			
			
			stmt.close();
			rs.close();
			rs2.close();
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return reimList;
		
	}

}
