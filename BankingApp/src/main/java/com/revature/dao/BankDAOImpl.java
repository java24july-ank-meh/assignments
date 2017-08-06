package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.domain.Accounts;
import com.revature.domain.BankUser;
import com.revature.util.ConnectionUtil;

public class BankDAOImpl implements BankDAO {

	@Override
	public void CreateBU(BankUser bu) {
		// opening a new connection
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			//storing class fields as future arguments
			String f = bu.getfName();
			String l = bu.getlName();
			String p = bu.getPhoneNum();
			String id = bu.getUname();
			String pw = bu.getPw();
			String em = bu.getEmail();
					
			
			// declare sql statement + arguments
			String sql = "INSERT INTO PERSON(FIRSTNAME, LASTNAME, PHONENUM,"
					+ " P_ID, P_PW, EMAIL) VALUES('" +f+ "', '" +l+ "', '"+p+"', '"+id
					+"', '"+pw+"', '"+em+"')";
			// create the statement, as part of the connection
			Statement stmt = conn.createStatement();
			// execute the statement 
			int nRowsAffected = stmt.executeUpdate(sql);
					
			System.out.println(nRowsAffected + " row created");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void ReadBU(BankUser bu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateBU(BankUser bu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteBU(BankUser bu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CreateAcc(BankUser bu) {
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			//storing class fields as future arguments
			int uID = bu.getUserID();
					
			
			// declare sql statement + arguments
			String sql = "INSERT INTO ACCOUNTS(USER_ID, BALANCE) VALUES("+uID+", "+0+")";
			// create the statement, as part of the connection
			Statement stmt = conn.createStatement();
			// execute the statement 
			int nRowsAffected = stmt.executeUpdate(sql);
					
			System.out.println(nRowsAffected + " row created");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void ReadAcc(Accounts ac) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void WithdrawAcc(Accounts ac) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DepositAcc(Accounts ac) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteAcc(Accounts ac) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Accounts> ReadAllAcc(BankUser bu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int GetUserID(BankUser bu) {
		PreparedStatement pstmt = null;
		String un = bu.getUname();
		int id = 0;
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT USER_ID FROM PERSON Where P_ID ='"+un+"'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				id = rs.getInt("USER_ID");
			}
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();} 
				catch(SQLException e) {e.printStackTrace();}
			}
		}
		return id;
		
	}


	@Override
	public Accounts GetAccInfo(BankUser bu, String str) {
		Accounts temp = new Accounts();
		PreparedStatement pstmt = null;
		int uid = bu.getUserID();
		int id = 0;
		int anum = 500;
		int bal = 20;
		String t = "";
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT USER_ID, ACCOUNTNUM, BALANCE, TYPEACC FROM ACCOUNTS Where USER_ID ="+uid+"";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				id = rs.getInt("USER_ID");
				anum = rs.getInt("ACCOUNTNUM");
				bal = rs.getInt("BALANCE");
				t = rs.getString("TYPEACC");
				temp.setAccountNum(anum);
				temp.setUserID(id);
				temp.setBalance(bal);
				temp.setTypea(t);
			}
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();} 
				catch(SQLException e) {e.printStackTrace();}
			}
		}
		return temp;
	}

	@Override
	public int GetAccNUM(BankUser bu, String str) {
		int num = 0;
		PreparedStatement pstmt = null;
		int uid = bu.getUserID();
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT ACCOUNTNUM FROM ACCOUNTS Where USER_ID ="+uid+" and TYPEACC = '"+str+"'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				num = rs.getInt("ACCOUNTNUM");
			}
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();} 
				catch(SQLException e) {e.printStackTrace();}
			}
		}
		return num;
	}


}
