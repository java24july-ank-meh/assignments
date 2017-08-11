package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.revature.utilities.ConnectionUtil;

public class ExtraDaoImpl implements ExtraDao {

	@Override
	public Map<String, Integer> returnUserRoles() {
		// TODO Auto-generated method stub
		Map<String, Integer> roles = new HashMap<String, Integer>();

		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		try(Connection conn = ConnectionUtil.getConectionProperties()){
			String sql1 = "Select * from UserRoles";

			pStmt1 = conn.prepareStatement(sql1);
			rs = pStmt1.executeQuery();

			while (rs.next()) {
				String un = rs.getString("User_Role");
				int urid = rs.getInt("Ur_ID");

				roles.put(un, urid);
			}

		}catch(SQLException se){
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pStmt1 != null) {
				try {
					pStmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return roles;
	}

	@Override
	public Map<String, Integer> returnReimbursementTypes() {
		Map<String, Integer> types = new HashMap<String, Integer>();

		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		try(Connection conn = ConnectionUtil.getConectionProperties()){
			String sql1 = "Select * from ReType";

			pStmt1 = conn.prepareStatement(sql1);
			rs = pStmt1.executeQuery();

			while (rs.next()) {
				String t = rs.getString("Rt_Type");
				int r = rs.getInt("Rt_ID");

				types.put(t, r);
			}

		}catch(SQLException se){
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pStmt1 != null) {
				try {
					pStmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return types;
	}

	@Override
	public Map<String, Integer> returnReimbursementStatus() {
		Map<String, Integer> status = new HashMap<String, Integer>();

		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		try(Connection conn = ConnectionUtil.getConectionProperties()){
			String sql1 = "Select * from ReStatus";

			pStmt1 = conn.prepareStatement(sql1);
			rs = pStmt1.executeQuery();

			while (rs.next()) {
				String t = rs.getString("Rs_Type");
				int s = rs.getInt("Rs_ID");

				status.put(t, s);
			}

		}catch(SQLException se){
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pStmt1 != null) {
				try {
					pStmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return status;
	}

}
