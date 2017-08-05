package com.revature.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.domain.BankUser;
import com.revature.util.ConnectionUtil;

public class Main {
	
	private static List<BankUser> allUsers = new ArrayList<>(); 

	public static void main(String[] args) {
		
		/*
		try(Connection c = ConnectionUtil.getConnection()){
			System.out.println("success?");
		} catch (SQLException e) {
			System.out.println("failed to connect");
			e.printStackTrace();
		}
		*/
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to banking app!");
		String input = "";
		while(!input.equalsIgnoreCase("exit")){
			System.out.println("Are you a returning user or a new user?");
			input = s.nextLine();
		}
	}
}
