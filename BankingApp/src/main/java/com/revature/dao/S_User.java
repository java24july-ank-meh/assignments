package com.revature.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.sql.*;
import com.revature.domain.Clients;
import com.revature.util.Connect;

public class S_User implements SuperUserFace {
	
	//Create a Scanner
	Scanner scan = new Scanner(System.in);
	
	public void addClient() {
		PreparedStatement pstmt = null;
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			
			//Get the infomation need to build a new entry
			System.out.println("Please enter your firstname: ");
			String f = scan.nextLine();
			System.out.println("Please enter your lastname: ");
			String l = scan.nextLine();
			System.out.println("Please type in a password: ");
			String p = scan.nextLine();
			
			//Create a prepared statement
			String add = "INSERT INTO Clients(C_FirstName, C_LastName, C_Password) "
					+ "VALUES(?,?,?)";
			pstmt = conn.prepareStatement(add);
			pstmt.setString(1, f);
			pstmt.setString(2, l);
			pstmt.setString(3, p);
			
			pstmt.executeQuery();
			System.out.println("Client " + f + " " + l + " has been added");
		} catch (SQLException | IOException e) {
			System.out.println("Failed to add client to Database");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
	}

	public void viewClient(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			String read = "SELECT * FROM Clients WHERE C_ID= ?";
			pstmt = conn.prepareStatement(read);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			System.out.println("Accessing Client Database");
			boolean hasResult = false;
			
			while(rs.next()) {
				hasResult = true;
				String clientId = rs.getString("C_ID");
				String clientFirstname = rs.getString("C_Firstname");
				String clientLastname = rs.getString("C_Lastname");
				String Password = rs.getString("C_Password");
				
				System.out.println("ClientID: " + clientId);
				System.out.println("FirstName: " + clientFirstname);
				System.out.println("LastName: " + clientLastname);
				System.out.println("ClientPassword:" + Password);
			}
			if(hasResult == false) {
				System.out.println("Client does not exist for this ID.");
				return;
			}
			
		} catch (SQLException | IOException e) {
			System.out.println("Failed to view client in Database");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
	}

	public List<Clients> viewAllClients() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Clients> clientList = new ArrayList<>();
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			String read = "SELECT * FROM Clients";
			pstmt = conn.prepareStatement(read);	
			rs = pstmt.executeQuery();
			System.out.println("Accessing Client Database");
			
			while(rs.next()) {
				int clientId = rs.getInt("C_ID");
				String clientFirstname = rs.getString("C_Firstname");
				String clientLastname = rs.getString("C_Lastname");
				String Password = rs.getString("C_Password");
				
				Clients c = new Clients(clientId, clientFirstname, clientLastname, Password);
				
				clientList.add(c);
			}
		} catch (SQLException | IOException e) {
			System.out.println("Failed to view clients in Database");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		return clientList;
	}

	public void updateClient(int c) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try(Connection conn = Connect.connectWithProps()){
			System.out.println("Please denote the type of update: ");
			System.out.println("1: Firstname change");
			System.out.println("2: Lastname change");
			System.out.println("3: Password change");
			int up = scan.nextInt();
			switch(up) {
				case 1: System.out.println("Please enter the update for the customer's firstname");
						String f_name = scan.next();
						String read = "UPDATE Clients SET C_FIRSTNAME =? WHERE C_ID=? ";
						pstmt =conn.prepareStatement(read);
						pstmt.setString(1, f_name);
						pstmt.setInt(2, c);
						pstmt.executeUpdate();
						System.out.println("Update complete");
					break;
				case 2: System.out.println("Please enter the update for the customer's lastname");
						String l_name = scan.next();
						String read1 = "UPDATE Clients SET C_LASTNAME =? WHERE C_ID=? ";
						pstmt =conn.prepareStatement(read1);
						pstmt.setString(1, l_name);
						pstmt.setInt(2, c);
						pstmt.executeUpdate();
						System.out.println("Update complete");
					break;
				case 3: System.out.println("Please enter the update for the customer's password");
						String p_name = scan.next();
						String read2 = "UPDATE Clients SET C_PASSWORD =? WHERE C_ID=? ";
						pstmt =conn.prepareStatement(read2);
						pstmt.setString(1, p_name);
						pstmt.setInt(2, c);
						pstmt.executeUpdate();
						System.out.println("Update complete");
					break;
				default:
					break;
			}
		}catch (SQLException | IOException e) {
			System.out.println("Failed to view clients in Database");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
	}

	public void deleteClient(int id) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			//Create a prepared statement to delete all Accounts connected to client
			String read = "SELECT * FROM Client_Accounts WHERE C_ID= ?";
			pstmt2 = conn.prepareStatement(read);
			pstmt2.setInt(1, id);
			
			boolean stopDelete = false;
			rs = pstmt2.executeQuery();
			while(rs.next()) {
				if(rs.getFloat("Acc_Value") == 0) {
					System.out.println("The account is empty. Now deleting account.");
					String remove = "DELETE FROM Client_Accounts WHERE Acc_ID=?";
					pstmt2 = conn.prepareStatement(remove);
					pstmt2.setInt(1, rs.getInt(1));
					pstmt2.executeUpdate();
					System.out.println("Account deleted successfully.");
				} else {
					System.out.println("Account " + rs.getInt(1)+ " still has money. Need to empty account of money first.");
					stopDelete = true;
					break;
				}
			}
			
			if(stopDelete == true) {
				return;
			}
			//Create a prepared statement to delete client
			String remove = "DELETE FROM Clients WHERE C_ID=?";
			pstmt = conn.prepareStatement(remove);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			System.out.println("Client has been succesfully deleted");
		} catch (SQLException | IOException e) {
			System.out.println("Failed to delete client from Database");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (pstmt2 != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
		
	}

	public void deleteAllClients() {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			//Create a prepared statement to delete all Accounts
			String read = "SELECT * FROM Client_Accounts";
			pstmt2 = conn.prepareStatement(read);
			
			boolean stopDelete = false;
			rs = pstmt2.executeQuery();
			while(rs.next()) {
				if(rs.getFloat("Acc_Value") == 0) {
					System.out.println("The account is empty. Now deleting account.");
					String remove = "DELETE FROM Client_Accounts WHERE Acc_ID=?";
					pstmt2 = conn.prepareStatement(remove);
					pstmt2.setInt(1, rs.getInt(1));
					pstmt2.executeUpdate();
					System.out.println("Account deleted successfully.");
				} else {
					System.out.println("Account " + rs.getInt(1)+ " still has money. Need to empty account of money first.");
					stopDelete = true;
					break;
				}
			}
			
			if(stopDelete == true) {
				return;
			}
			//Create a prepared statement to delete client
			String remove = "DELETE FROM Clients";
			pstmt = conn.prepareStatement(remove);
			
			pstmt.executeUpdate();
			System.out.println("All clients has been succesfully deleted");
		} catch (SQLException | IOException e) {
			System.out.println("Failed to delete clients from Database");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (pstmt2 != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
	}

}
