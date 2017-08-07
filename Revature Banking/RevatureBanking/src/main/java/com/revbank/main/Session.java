package com.revbank.main;

import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

import com.revbank.dao.*;
import com.revbank.util.ConsoleDevice;

public class Session {
	
	// Is session active?
	private Boolean isActive;
	
	// Username and password fields
	private String username;
	private char[] password;
	
	// Console wrapper
	private ConsoleDevice consoleDev;
	
	// DAOs
	private UserDAO userdao;
	private AdminDAO admindao;
	
	
	public Session() {
		this.isActive = true;
		this.consoleDev = new ConsoleDevice();
		this.admindao = new AdminDAOImpl();
		this.userdao = new UserDAOImpl();
		
		System.out.println("Welcome to Revature Banking!\n\n");
		
		while(this.isActive == true) {
			
			String command = "";
		
			try {
				
				command = consoleDev.readLine("Please type the number of an option: \n"
													+ "1) Login as a user\n"
													+ "2) Login as an admin\n"
													+ "3) Create new account\n"
													+ "4) Exit\n\n");
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch (Integer.parseInt(command)) {
			case 1:
				this.login(false);
				break;
			case 2:
				this.login(true);
				break;
			case 3:
			case 4:
				this.endSession();
				break;
			default:
				System.out.println("Unknown command!\n");
				break;
			}
		
		}
		
		
	}
	
	public void login(Boolean admin) {
		try {
			this.username = consoleDev.readLine("Please enter your username: ");
			this.password = consoleDev.readPassword("Please enter your password: ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (admin == true) {
			//TODO
		} else {
			userdao.accountLogin(this.username, this.password.toString());
		}
		
	}
	
	public void endSession() {
		this.isActive = false;
		System.out.println("Goodbye!");
	}
}
