package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.domain.Login;
import com.revature.domain.User;
import com.revature.util.ConnectionUtil;

public class superUserDAOImpl extends LoginDAOImpl implements superUserDAO{

	@Override
	public void readAllUsers() {
		PreparedStatement pstmt = null;
		ArrayList<User> arr = new ArrayList<User>();
		try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ACCOUNT";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("ACCOUNT_ID");
                int acc_num = rs.getInt("ACCOUNT_NUMBER");
                String last = rs.getString("LASTNAME");
                String first = rs.getString("FIRSTNAME");
                int bal=rs.getInt("ACCOUNT_BALANCE");
                
                User u = new User(id, acc_num, last, first,bal);
                arr.add(u);
            }
            rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
            if (pstmt!=null) {
                try {pstmt.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
		System.out.println(arr);
	}

	@Override
	public boolean deleteUser(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			conn.setAutoCommit(false);
            String sql = "DELETE FROM ACCOUNT WHERE ACCOUNT_ID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowsAff=pstmt.executeUpdate();
            System.out.println("Account "+id+" was deleted");
            conn.setAutoCommit(true);
            pstmt.close();
            return true;
            
            
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
            if (pstmt!=null) {
                try {pstmt.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
		return false;
	}

	@Override
	public boolean updateLastName(int id, String str) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			conn.setAutoCommit(false);
            String sql = "UPDATE ACCOUNT SET LASTNAME=? WHERE ACCOUNT_ID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, str);
            pstmt.setInt(2, id);
            int rowsAff=pstmt.executeUpdate();
            System.out.println("Account "+id+" was updated and changed last name");
            conn.setAutoCommit(true);
            pstmt.close();
            return true;
            
            
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
            if (pstmt!=null) {
                try {pstmt.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
		return false;
	}
	
	public boolean updateFirstName(int id, String str) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			conn.setAutoCommit(false);
            String sql = "UPDATE ACCOUNT SET FIRSTNAME=? WHERE ACCOUNT_ID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, str);
            pstmt.setInt(2, id);
            int rowsAff=pstmt.executeUpdate();
            System.out.println("Account "+id+" was updated and changed first name");
            conn.setAutoCommit(true);
            pstmt.close();
            return true;
            
            
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
            if (pstmt!=null) {
                try {pstmt.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
		return false;
	}
	
	
	
	public boolean updateBalance(int id, int amount) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			conn.setAutoCommit(false);
            String sql = "UPDATE ACCOUNT SET ACCOUNT_BALANCE=? WHERE ACCOUNT_ID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, amount);
            pstmt.setInt(2, id);
            int rowsAff=pstmt.executeUpdate();
            System.out.println("Account "+id+" was updated, changed balance");
            conn.setAutoCommit(true);
            pstmt.close();
            return true;
            
            
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
            if (pstmt!=null) {
                try {pstmt.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
		return false;
	}
	
	public boolean updateUserName(int id, String str) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			conn.setAutoCommit(false);
            String sql = "UPDATE LOGIN SET USERNAME=? WHERE ACCOUNT_ID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, str);
            pstmt.setInt(2, id);
            int rowsAff=pstmt.executeUpdate();
            System.out.println("Account "+id+" was updated, changed Username");
            conn.setAutoCommit(true);
            pstmt.close();
            return true;
            
            
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
            if (pstmt!=null) {
                try {pstmt.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
		return false;
	}
	
	public boolean updatePassword(int id, char[] str) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			conn.setAutoCommit(false);
            String sql = "UPDATE LOGIN SET USER_PASSWORD=? WHERE ACCOUNT_ID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.copyValueOf(str));
            pstmt.setInt(2, id);
            int rowsAff=pstmt.executeUpdate();
            System.out.println("Account "+id+" was updated, password changed");
            conn.setAutoCommit(true);
            pstmt.close();
            return true;
            
            
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
            if (pstmt!=null) {
                try {pstmt.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
		return false;
	}

}
