package com.revature.dao;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.revature.domain.Account;
import com.revature.util.ConnectionUtility;

public class AccountDaoImpl implements AccountDao {

	public void createAccount(Account a) {

		if(a != null) {
		PreparedStatement pStmt1 = null;

		//opening new connection
		try(Connection conn = ConnectionUtility.getConectionProperties()){

			//storing class fields
			String n = a.getAccountName();
			int u = a.getUserID();

			//declare sql statement + arguments
			String sql1 = "Insert Into BAAccount(Account_name, MyUser_ID) Values('?', ?)";

			//create the statement as part of connection 
			pStmt1 = conn.prepareStatement(sql1);

			//insert vars
			pStmt1.setString(2, n);
			pStmt1.setInt(3, u);

			//execute query, get result set
			pStmt1.executeUpdate();

			System.out.println("Account created.");

		}catch(Exception e) {
			e.printStackTrace();
		}finally {

			if(pStmt1 != null) {
				try {
					pStmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}

		}
		}

	}

	public Account readAccount(int id) {

		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		Account a = new Account();

		try(Connection conn = ConnectionUtility.getConectionProperties()){
			String sql1 = "Select * From BAAccount Where Account_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, id);

			rs = pStmt1.executeQuery();

			while(rs.next()) {
				String aid = rs.getString("Account_ID");
				String name = rs.getString("Account_Name");
				String balance = rs.getString("Balance");
				a.setAccountID(Integer.parseInt(aid));
				a.setAccountName(name);
				a.setBalance(Double.parseDouble(balance));
				System.out.println("Account ID: "+aid);
				System.out.println("\t Name: "+name);
				System.out.println("\t Balance: "+balance);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pStmt1 != null) {
				try {
					pStmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return a;
	}

	public List<Account> readAllAccount(int id) {

		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		List<Account> theList = new ArrayList<Account>();

		try(Connection conn = ConnectionUtility.getConectionProperties()){
			String sql1 = "Select * From BAAccount Where Account_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, id);

			rs = pStmt1.executeQuery();

			while(rs.next()) {

				String aid = rs.getString("Account_ID");
				String uid = rs.getString("MyUser_ID");
				String name = rs.getString("Account_Name");
				String balance = rs.getString("Balance");

				System.out.println("Account ID: "+aid);
				System.out.println("\t User ID: "+uid);
				System.out.println("\t Name: "+name);
				System.out.println("\t Balance: "+balance);
				Account a = new Account(Integer.parseInt(aid),Integer.parseInt(uid),name,Double.parseDouble(balance));
				theList.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pStmt1 != null) {
				try {
					pStmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return theList;
	}

	public List<Account> readAllAccount() {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		List<Account> theList = new ArrayList<Account>();

		try(Connection conn = ConnectionUtility.getConectionProperties()){
			String sql1 = "Select * From BAAccount";

			pStmt1 = conn.prepareStatement(sql1);

			rs = pStmt1.executeQuery();

			while(rs.next()) {

				String aid = rs.getString("Account_ID");
				String uid = rs.getString("MyUser_ID");
				String name = rs.getString("Account_Name");
				String balance = rs.getString("Balance");

				System.out.println("Account ID: "+aid);
				System.out.println("\t User ID: "+uid);
				System.out.println("\t Name: "+name);
				System.out.println("\t Balance: "+balance);
				Account a = new Account(Integer.parseInt(aid),Integer.parseInt(uid),name,Double.parseDouble(balance));
				theList.add(a);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pStmt1 != null) {
				try {
					pStmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return theList;
	}

	public boolean deleteAccount(Account a) {
		boolean done = false;

		if(a.getBalance() == 0 && a != null) {
			PreparedStatement pStmt1 = null;

			try(Connection conn = ConnectionUtility.getConectionProperties()){
				String sql1 = "Delete From BAAccount Where Account_ID = ?";

				pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setInt(1, a.getAccountID());

				pStmt1.executeUpdate();
				System.out.println("Account deleted completely.");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(pStmt1 != null) {
					try {
						pStmt1.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			done = true;
		}

		return done;
	}
	//-------------------------------------------------------------------
	public void depositMoney(int accid, double amt) {

		Account a = null;
		a = readAccount(accid);

		if(a != null) {
			PreparedStatement pStmt1 = null;

			try(Connection conn = ConnectionUtility.getConectionProperties()){
				String sql1 = "Update From BAAccount Set Balance = ? Where Account_ID = ?";

				pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setDouble(1, a.getBalance());
				pStmt1.setInt(2, a.getAccountID());

				pStmt1.executeUpdate();
				System.out.println("Amount deposited and Account updated.");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(pStmt1 != null) {
					try {
						pStmt1.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	public void depositMoney(Account a, double amt) {

		if(a != null) {

			PreparedStatement pStmt1 = null;

			try(Connection conn = ConnectionUtility.getConectionProperties()){
				String sql1 = "Update From BAAccount Set Balance = ? Where Account_ID = ?";

				pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setDouble(1, a.getBalance());
				pStmt1.setInt(2, a.getAccountID());

				pStmt1.executeUpdate();
				System.out.println("Amount deposited and Account updated.");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(pStmt1 != null) {
					try {
						pStmt1.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public boolean withdrawMoney(int accid, double amt) {
		boolean done = false;

		Account a = null;
		a = readAccount(accid);

		if(a.getBalance() > amt && a != null) {
			PreparedStatement pStmt1 = null;

			try(Connection conn = ConnectionUtility.getConectionProperties()){
				String sql1 = "Update From BAAccount Set Balance = ? Where Account_ID = ?";

				pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setDouble(1, a.getBalance());
				pStmt1.setInt(2, a.getAccountID());

				pStmt1.executeUpdate();
				System.out.println("Amount withdrawn and Account updated.");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(pStmt1 != null) {
					try {
						pStmt1.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			done = true;
		}

		return done;
	}

	public boolean withdrawMoney(Account a, double amt) {
		boolean done = false;
		
		if(a != null) {
			if(a.getBalance() > amt) {
				PreparedStatement pStmt1 = null;

				try(Connection conn = ConnectionUtility.getConectionProperties()){
					String sql1 = "Update From BAAccount Set Balance = ? Where Account_ID = ?";

					pStmt1 = conn.prepareStatement(sql1);
					pStmt1.setDouble(1, a.getBalance());
					pStmt1.setInt(2, a.getAccountID());

					pStmt1.executeUpdate();
					System.out.println("Amount withdrawn and Account updated.");
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(pStmt1 != null) {
						try {
							pStmt1.close();
						}catch(SQLException e) {
							e.printStackTrace();
						}
					}
				}
				done = true;
			}
		}
		
		return done;
	}

}