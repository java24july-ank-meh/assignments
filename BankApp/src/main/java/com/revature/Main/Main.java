package com.revature.Main;

import com.revature.dao.UserDaoimpl;

import java.util.Scanner;
import java.io.IOException;
import java.math.*;
import com.revature.bean.User;
import com.revature.connections.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDaoimpl dao = new UserDaoimpl();
		User user1 = new User();
		String in;
		String in2;
		Scanner scan = new Scanner(System.in);

		try {
			user1 = dao.read_user("CALEB");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// dao.createNewUser("Gary", "password", 0);
		// dao.createAccount(2, "FIRST");
		// dao.Deposit(1, 100.00);
		// dao.deleteAccount(2);
		// dao.

		System.out.println("new user? (Y/N)");
		in = scan.nextLine();
		if (in.equals("Y") || in.equals("y")) {
			
			System.out.println("please create a username:\n ");
			in = scan.nextLine();
			
			System.out.println("and a password: ");
			in2 = scan.nextLine();
			dao.createNewUser(in, in2, 0);
			
			System.out.println("user created");
			System.out.println("do you wish to exit? (Y/N)");
		} 
		else if (in.equals("n") || in.equals("N")) {
			
			System.out.println("Admin?\n");
			in = scan.nextLine();
			
			if (in.equals("Y") || in.equals("y")) {
				//call admin login here
			}
			if (in.equals("N") || in.equals("n")) {
				//call regular login
				
			}

		} else {
			System.out.println("not an appropriate answer");
		}
	}

}
