package com.revature.implement;

import java.sql.*;
import com.revature.bankTables.Account;
import com.revature.bankTables.Transaction;
import com.revature.bankTables.User;
import com.revature.conutil.ConnectionUtil;
import com.revature.dao.BankDao;

public class BankDAOImpl implements BankDao{


	public void createUser(User a) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String uFirst = a.getFirstName();
			String uLast = a.getLastName();
			String uUser = a.getUserName();
			String uPass = a.getPassWord(); 
			int uPriv = a.getPriv();
			
			//creating a user here 
			String userCreate = "INSERT INTO BANKUSER (FIRSTNAME, LASTNAME, USERNAME, PASSIN, ADMIN_PRIV) VALUES ('"+uFirst+"', '"+uLast+"', '"+uUser+"', '"+uPass+"', '"+uPriv+")";
			Statement stmtUCreate = conn.createStatement();
			//int nRowsAffected = stmtUCreate.executeUpdate(userCreate);
			stmtUCreate.executeQuery(userCreate);
			
			System.out.println("Username "+uUser+" created");
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
	}

	public String readUser(String userName, String passin) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String results ="";
		try(Connection conn = ConnectionUtil.getConnection()){
			String getInfo = "SELECT FIRSTNAME, LASTNAME FROM BANKUSER WHERE USERNAME = "+userName+ " AND PASSIN = "+passin;
			//Statement stmtURead = conn.createStatement();
			stmt = conn.prepareStatement(getInfo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				 results = (rs.getString("FIRSTNAME")+" "+rs.getString("LASTNAME"));
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return results;
	}

	public void updateUser(User a, String firstName, String lastName, String passWord, String userName) {
		PreparedStatement updateStmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String updateInfo = "UPDATE BANKUSER SET FIRSTNAME = '"+firstName+"', LASTNAME = '"+lastName+"', PASSIN = '"+passWord+"' "
					+ "WHERE USERNAME = "+userName;
			updateStmt = conn.prepareStatement(updateInfo);
			updateStmt.executeQuery();
			a.setFirstName(firstName);
			a.setLastName(lastName);
			a.setPassWord(passWord);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteUser(User a) {
		PreparedStatement deleteStmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String deleteUser = "DELETE FROM BANKUSER WHERE USERNAME = "+a.getUserName();
			deleteStmt = conn.prepareStatement(deleteUser);
			deleteStmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void createAccount(Account a) {
		// TODO Auto-generated method stub
		
	}

	public void readAccount(int id) {
		// TODO Auto-generated method stub
		
	}

	public void updateAccount(Account a) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAccount(Account a) {
		// TODO Auto-generated method stub
		
	}

	public void createTransaction(Transaction t) {
		// TODO Auto-generated method stub
		
	}

	public void readTransaction(int id) {
		// TODO Auto-generated method stub
		
	}

	public void updateTransaction(Transaction t) {
		// TODO Auto-generated method stub
		
	}

	public void deleteTransaction(Transaction t) {
		// TODO Auto-generated method stub
		
	}

}
