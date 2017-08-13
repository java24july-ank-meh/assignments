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

	
	public void test() throws IOException {
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT U_ID FROM ERS_USERS";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("attempt");
			System.out.println(rs.getFetchSize());
			while(rs.next()) {
				System.out.println(rs.getInt("U_ID"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {pstmt.close();}
				catch(SQLException e) {e.printStackTrace();
				}
			}
	}
		System.out.println("end");
	}
	
	
	
	
	
	
	
	
	public User login(String username, String password) throws IOException, SQLException {
		CallableStatement cs = null;
		int user_id;
		String user_role;
		User u = new User();
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
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
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return u;
		}
		
}