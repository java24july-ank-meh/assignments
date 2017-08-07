package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.revature.domain.Login;
import com.revature.domain.User;
import com.revature.util.ConnectionUtil;

public class LoginDAOImpl implements LoginDAO {

	public boolean login(Login lg) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT USERNAME FROM LOGIN WHERE USERNAME=? AND USER_PASSWORD=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lg.getUserName());
			pstmt.setString(2, String.copyValueOf(lg.getPassword()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean createUser(User u) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ACCOUNT(ACCOUNT_NUMBER,LASTNAME,FIRSTNAME,ACCOUNT_BALANCE) VALUES(?,?,?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getAccountNumber());
			pstmt.setString(2, u.getLastName());
			pstmt.setString(3, u.getFirstName());
			int rowsAff = pstmt.executeUpdate();
			System.out.println(rowsAff + " row created in Account table");
			conn.setAutoCommit(true);

			int id;
			sql = "SELECT ACCOUNT_ID FROM ACCOUNT WHERE ACCOUNT_NUMBER=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getAccountNumber());
			rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt("ACCOUNT_ID");
			else
				return false;

			conn.setAutoCommit(false);
			sql = "INSERT INTO LOGIN VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, u.getLogininfo().getUserName());
			pstmt.setString(3, String.copyValueOf(u.getLogininfo().getPassword()));
			rowsAff = pstmt.executeUpdate();
			System.out.println(rowsAff + " row created in Login talbe");
			conn.setAutoCommit(true);

			pstmt.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public User getUserInfo(Login l) {
		User u;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int id=0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT ACCOUNT_ID FROM LOGIN WHERE USERNAME=? AND USER_PASSWORD=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, l.getUserName());
			pstmt.setString(2, String.copyValueOf(l.getPassword()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id=rs.getInt("ACCOUNT_ID");
				sql="SELECT * FROM ACCOUNT WHERE ACCOUNT_ID=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs=pstmt.executeQuery();
				int account_num=0;
				String lastname="";
				String firstname="";
				int bal=0;
				while(rs.next()) {
					account_num=rs.getInt("ACCOUNT_NUMBER");
					lastname=rs.getString("LASTNAME");
					firstname=rs.getString("FIRSTNAME");
					bal=rs.getInt("ACCOUNT_BALANCE");
				}
				u=new User(l,id,account_num,lastname,firstname,bal);
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
