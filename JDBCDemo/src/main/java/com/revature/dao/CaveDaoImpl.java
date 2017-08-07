package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Cave;
import com.revature.util.ConnectionUtil;

public class CaveDaoImpl implements CaveDao{
	@Override
	public void createCave(Cave c) {
		// OPEN NEW CONNECTION
		try(Connection conn = ConnectionUtil.getConnection()){
			// STORE CLASS FIELDS AS FUTURE ARGUMENTS
			String n = c.getName();
			int m = c.getMaxBears();
			System.out.println(n + "  " + m);
			// DECLARE SQL STATEMENT + ARGUMENTS
			String sql = "INSERT INTO CAVE(CAVE_NAME, MAX_BEARS) VALUES('" + n + "', " + m + ")";
			
			// CREATE THE STATEMENT, AS PART OF THE CONNECTION
			Statement stmt = conn.createStatement();
			
			// EXECUTE THE STATEMENT, RETURN NUMBER OF ROWS AFFECTED
			int nRowsAffected = stmt.executeUpdate(sql);
			
			System.out.println(nRowsAffected + " rows created.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void readCave(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM CAVE WHERE CAVE_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String caveid = rs.getString("CAVE_ID");
				String cavename = rs.getString("CAVE_NAME");
				
				System.out.println("CaveID: " + caveid);;
				System.out.println("CaveName: " + cavename);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if(rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
	}
	@Override
	public List<Cave> readAllCaves() {
		PreparedStatement pstmt = null;
		List<Cave> caves = new ArrayList<>();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM CAVE";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("CAVE_ID");
				String name = rs.getString("CAVE_NAME");
				int maxBears = rs.getInt("MAX_BEARS");
				
				Cave c = new Cave(id,name,maxBears);
				caves.add(c);
			}
			rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				if (pstmt != null) {
					try { pstmt.close();} 
					catch(SQLException e) {e.printStackTrace();}}
				}
			return caves;
		}
	public void updateCave(Cave c) {
		// TODO Auto-generated method stub
		
	}

	public void deleteCave(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void feedBear(int bid, int amt) {
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL SP_FEED_BEAR(?,?)}";
			cs = conn.prepareCall(sql);
			
			cs.setInt(1, amt);
			cs.setInt(2,  amt);
			
			cs.execute();
			Boolean result = cs.execute();
			if(result)
				System.out.println("Fed Bear");
			else
				System.out.println("Failed, and was eaten");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(cs != null) {
				try {
					cs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
