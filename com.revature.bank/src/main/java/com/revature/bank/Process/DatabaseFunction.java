package com.revature.bank.Process;

import java.sql.*;
import java.util.Scanner;

import com.revature.bank.Connection.ConnectionUtil;
import com.revature.bank.DAO.DAOFunctImp;

public class DatabaseFunction extends Thread{

	public void startUpLogo() {
		System.out.println("         _._._                       _._._\r\n" + 
				"        _|   |_                     _|   |_\r\n" + 
				"        | ... |_._._._._._._._._._._| ... |\r\n" + 
				"        | ||| |  o NATIONAL BANK o  | ||| |\r\n" + 
				"        | \"\"\" |  \"\"\"    \"\"\"    \"\"\"  | \"\"\" |\r\n" + 
				"   ())  |[-|-]| [-|-]  [-|-]  [-|-] |[-|-]|  ())\r\n" + 
				"  (())) |     |---------------------|     | (()))\r\n" + 
				" (())())| \"\"\" |  \"\"\"    \"\"\"    \"\"\"  | \"\"\" |(())())\r\n" + 
				" (()))()|[-|-]|  :::   .-\"-.   :::  |[-|-]|(()))()\r\n" + 
				" ()))(()|     | |~|~|  |_|_|  |~|~| |     |()))(()\r\n" + 
				"    ||  |_____|_|_|_|__|_|_|__|_|_|_|_____|  ||\r\n" + 
				" ~ ~^^ @@@@@@@@@@@@@@/=======\\@@@@@@@@@@@@@@ ^^~ ~\r\n" + 
				"      ^~^~                                ~^~^");
	}
	
	
	public void loginMenu() {//prints a 
		Scanner inu = new Scanner(System.in);
		System.out.println("1: User Login.");
		System.out.println("2: New User Registration.");
		System.out.println("0: Exit Bank App.");
		
		int id = 0;
		int choice = inu.nextInt();
		
		switch(choice) {
			case 1:
				System.out.println("Enter your first name: ");
				String fname = inu.nextLine();
				fname = inu.nextLine();
				System.out.println("Enter your phone number: ");
				int phone = inu.nextInt();
				
				id = userLogin(fname, phone);
				userMenu(id);
				break;
			
			case 2: 
				userRegistration();
				loginMenu();
				break;
				
			case 0:
				System.out.println("Thanks! Good bye!");
				break;
			
		}
		inu.close();
	}
	
	public void userMenu(int id) {//this will display user options 
		
		System.out.println("logged in!");
		DAOFunctImp dao = new DAOFunctImp();
		Scanner in = new Scanner(System.in);
		System.out.println("1: Add Credit.");
		System.out.println("2: Withdraw Credit.");
		System.out.println("3: Erase This Account.");
		System.out.println("4: View Balance");
		System.out.println("0: Log out.");
		
		boolean state = false;
		int choice = in.nextInt();
		
		do {
			
			switch(choice) {
				case 1:
					System.out.println("not yet implemented \n want something else?");
					choice = in.nextInt();
				break;
				case 2:
					System.out.println("not yet implemented \n want something else?");
					choice = in.nextInt();
				break;
				case 3:
					System.out.println("Are you sure? Y/N");
					String so = in.nextLine();
					if(so.equals("Y") || so.endsWith("y")) {
						dao.deletePerson(id);
						System.out.println("Logging out...");
						state = false;
					}
					else {
						System.out.println("Is anything else I can do?");
						choice = in.nextInt();
					}
					
				break;
				
				case 4:
					dao.readBal(id);
					System.out.println("Is anything else I can do?");
					choice = in.nextInt();
				break;
				
				case 0:
					System.out.println("Logged out...");
					state = false;
				break;
				
			}
			
		}while(state = false);
		
		in.close();
	}
	
	public void userRegistration() {
		Scanner in = new Scanner(System.in);
		DAOFunctImp dao = new DAOFunctImp();
		Person p = new Person();
		
		String fname, lname, email, address, city, state;
		int phone;
		
		System.out.println("Please enter your first name: ");
		fname = in.nextLine();
		System.out.println("Please enter your last name: ");
		lname = in.nextLine();
		System.out.println("Please enter your email: ");
		email = in.nextLine();
		System.out.println("Please enter your address: ");
		address = in.nextLine();
		System.out.println("Please enter your city: ");
		city = in.nextLine();
		System.out.println("Please enter your state: ");
		state = in.nextLine();
		System.out.println("Please enter your phone number: ");
		phone = in.nextInt();
		
		p.setUser_fname(fname);
		p.setUser_lname(lname);
		p.setUser_email(email);
		p.setUser_address(address);
		p.setUser_city(city);
		p.setUser_state(state);
		p.setUser_phone(phone);
		
		dao.createPerson(p);
		
		in.close();
	}
	
	public int userLogin(String fname, int phone) {//this method is used as login for a user and will return the id or -1
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int id = -1;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			
			String sql = "SELECT user_id FROM user_table WHERE user_fname = ? AND user_phone = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fname);
			pstmt.setInt(2, phone);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				id = rs.getInt("user_id");
			}
			
			return id;
			
		}catch(SQLException se) {
			
			if(pstmt != null) {
				System.out.println("problem reading the database.");
				try {
					pstmt.close();
				}catch(SQLException sqle) {
					sqle.printStackTrace();
				}
				
			}
			
			if(rs != null) {
				System.out.println("problem reading the database.");
				try {
					rs.close();
				}catch(SQLException sqlex) {
					sqlex.printStackTrace();
				}
			}
			
			return -1;
		}
	}

	public void withdrawMoney(int id) {
		
	}
	
	public void addMoney(int id) {
		
	}
}	
	
