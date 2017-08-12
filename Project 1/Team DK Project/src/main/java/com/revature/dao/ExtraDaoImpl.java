package com.revature.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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
				String t = rs.getString("Rs_status");
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
	//--------------------------------
	@Override
	public void addPhoto(int rid,byte[] pdata) {
	

		PreparedStatement pStmt1 = null;
		String sql1 = "Update Reimbursements Set R_Reciept=? Where R_ID=?";
		
		try (Connection conn = ConnectionUtil.getConectionProperties()) {

		pStmt1 = conn.prepareStatement(sql1);
		
		InputStream is = new ByteArrayInputStream(pdata);
		pStmt1.setBlob(1, is);
		
		pStmt1.setInt(2, rid);
		
		pStmt1.execute();
		
		}catch(SQLException se) {
			se.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (pStmt1 != null) {
				try {
					pStmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	}

	public void addPhoto(int rid,Blob pdata) {
		//  SqlParameter photo=new SqlParameter("reciept", SqlDbType.VarBinary);
//		   photo.Value=p.PhotoData;

		PreparedStatement pStmt1 = null;
		String sql1 = "Update Reimbursements Set R_Reciept=? Where R_ID=?";
		
		try (Connection conn = ConnectionUtil.getConectionProperties()) {

		pStmt1 = conn.prepareStatement(sql1);
		
		pStmt1.setBlob(1, pdata);

		pStmt1.setInt(2, rid);
		
		pStmt1.executeUpdate();
		
		}catch(SQLException se) {
			se.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (pStmt1 != null) {
				try {
					pStmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
	
}
