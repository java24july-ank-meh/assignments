package com.revature.main;

import java.util.Scanner;

import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;
import com.revature.domain.Accounts;
import com.revature.domain.BankUser;

public class Main {
	public static void main(String[] args) {
		
		BankDAO dao = new BankDAOImpl();
		Scanner sc = new Scanner(System.in);
		String str = "Savings";
		String uname = "";
		String pass = "";
		Accounts a = new Accounts();
		BankUser cur = new BankUser();
		System.out.println("Enter your username: ");
		uname = sc.nextLine();
		System.out.println("Enter your password: ");
		pass = sc.nextLine();
		cur = dao.ReadBU(uname, pass);
		System.out.println(cur);
		cur = dao.ReadBU("aocon", "ppasses");
		System.out.println(cur);
		
		//BankUser c = new BankUser("Anabel", "ocon", "4567890123", "ancon", "ppasses", "ancon@mail.com");
		//dao.CreateBU(c);
		//c.setUserID(dao.GetUserID(c));
		//dao.CreateAcc(c);
		
		//a.setAccountNum(dao.GetAccNUM(cur, str));
		//System.out.println(a);
		//a = dao.GetAccInfo(cur, str);
		//System.out.println(a);
		//dao.DepositAcc(a, 50);
		//dao.WithdrawAcc(a, 25);
		
		
		//System.out.println("Welcome!");
		//System.out.println("Do you have an account with us? ");
		//input = sc.nextLine();
		/*while(true) {
			if(input.equalsIgnoreCase("no")) {
				System.out.println("Would you like to make an account? ");
				input = sc.nextLine();
				if(input.equalsIgnoreCase("yes")) {
					System.out.println("Alright, Lets make you an account");
					break;
				}else if(input.equalsIgnoreCase("no")) {
					System.out.println("Closing app.");
					break;
				}
			}
			else if(input.equalsIgnoreCase("yes")) {
				System.out.println("lets get you into your account");
				break;
			}
			else if(input.equalsIgnoreCase("quit")) {
				System.out.println("Closing app.");
				break;
			}
		}*/
	}
		
		
		//BankDAO dao = new BankDAOImpl();
		//BankUser c = new BankUser("Eleazar", "Rosales", "1234567890", "erosales", "ppass", "eli@mail.com");
		//dao.CreateBU(c);
		
		//dao.readCave(1);
		//dao.createCave(c);
}
