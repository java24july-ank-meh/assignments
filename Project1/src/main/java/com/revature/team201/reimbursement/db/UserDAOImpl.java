package com.revature.team201.reimbursement.db;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import com.revature.team201.reimbursement.users.Employee;
import com.revature.team201.reimbursement.users.Manager;
import com.revature.team201.reimbursement.users.Role;
import com.revature.team201.reimbursement.users.Status;
import com.revature.team201.reimbursement.users.User;
import com.revature.team201.reimbursement.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO{
	
	public User login(String username, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user_roles " +
					"INNER JOIN uusers ON uusers.ur_id = user_roles.ur_id " +
					"WHERE u_username = ? AND u_password = ?";
		
		User user = null;
		try (Connection connection = ConnectionUtil.getConnection()){
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// USER CREDENTIALS ACCEPTED. CREATING USER
				Integer userId = rs.getInt("U_ID");
				String userName = rs.getString("U_USERNAME");
				String firstName = rs.getString("U_FIRSTNAME");
				String lastName = rs.getString("U_LASTNAME");
				String eMail =  rs.getString("U_EMAIL");
				Role role = Role.valueOf(rs.getString("UR_ROLE"));
			
				if(role.equals(Role.MANAGER))
					user = new Manager(userId, userName, firstName, lastName, eMail);
				// Else creates an Employee
				else
					user = new Employee(userId, userName, firstName, lastName, eMail);
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		return user;
	}
	
	@Override
	public BufferedImage getReimbursementImage(Integer reimbursementId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		BufferedImage bufferedImage = null;
		
		try (Connection connection = ConnectionUtil.getConnection()){			
			String sql = "SELECT * FROM reimbursements WHERE r_id = ?";
			
			ps = connection.prepareStatement(sql);
			ps.setInt(1, reimbursementId);
			
			rs = ps.executeQuery(); 
			
			while (rs.next()) {
				
				Blob blob = rs.getBlob("r_receipt");
				
				if (blob != null) {
					InputStream inputStream = blob.getBinaryStream();
					bufferedImage = ImageIO.read(inputStream);
				}
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try { rs.close(); } catch (SQLException ex) { ex.printStackTrace();}
			}
			if (rs != null) {
				try { rs.close(); } catch (SQLException ex) { ex.printStackTrace();}
			}
		}
		
		return bufferedImage;
	}
	@Override
	public void addUser(User user, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO UUSERS (U_USERNAME, U_PASSWORD, U_FIRSTNAME, "
				+ "U_LASTNAME, U_EMAIL, UR_ID) VALUES(?, ?, ?, ?, ?, ?)";
		try (Connection connection = ConnectionUtil.getConnection()){
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, password);
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setString(5, user.geteMail());
			Role role = user.getUserRole();
			Integer i = getRoleId(role);
			pstmt.setInt(6, i);
			
			rs = pstmt.executeQuery();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
	}
	public boolean checkForUserName(String username) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM UUSERS WHERE U_USERNAME = ?";
		Boolean flag = false;
		try (Connection connection = ConnectionUtil.getConnection()){
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				flag = true;
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		return flag;
	}
	public Integer getRoleId(Role role) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer roleId = null;
		
		try (Connection connection = ConnectionUtil.getConnection()){
			String statement = "SELECT * FROM USER_ROLES WHERE UR_ROLE = ?";
			
			ps = connection.prepareStatement(statement);
			ps.setString(1, role.name());
			
			rs = ps.executeQuery();
			
			if(rs.next())
				roleId = rs.getInt("UR_ID");	
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return roleId;
	}
}
