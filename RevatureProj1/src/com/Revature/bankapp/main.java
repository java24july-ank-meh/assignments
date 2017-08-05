package com.Revature.bankapp;

import java.sql.*;
import java.util.*;


/* README
 * Base Menu: Case 1: Login, Case 2: Register, 
 * Case 1 Menu:
 * 	Login Menu
 * 		Case 1: Deposit
 * 			Get Total
 * 			Add deposit amount
 * 			Use update command to update total.
 * 			Commit
 * 		Case 2: Withdraw
 * 			Get Total
 * 			If WIthdraw < Total
 * 			Subtract Total
 * 			Else give error and break loop.
 * 			Commit
 * 		Case 3: Delete
 * 			Get Total
 * 			IF Total = 0, drop user.
 * 			Commit
 * 		Case 4: Exit
 * 	Login Fail, Loop back to login menu.
 * Case 2 Menu:
 * 		Enter Username
 * 		Enter Password
 * 		Confirm Password
 * 		Create new row.
 * 		
 */
public class main {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		driver();
	}

	private static void driver() throws InterruptedException {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter 1 to login, 2 to register, 3 to Exit...");
		String case1 = in.nextLine();
		while(!case1.equals("3")){
			switch(case1){
				case("1"): Sessionhandler SH1 = new Sessionhandler(); SH1.login(); break;
				case("2"): Sessionhandler SH2 = new Sessionhandler(); SH2.register(); break;
			}
			System.out.println("Enter 1 to login, 2 to register, 3 to Exit...");
			case1 = in.nextLine();
		}
		System.out.println("EXITING APPLICATION......");
	}

}
