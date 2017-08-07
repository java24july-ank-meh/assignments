package com.revature.main;

import java.util.Scanner;

import com.revature.controllers.LoginController;
import com.revature.models.User;

public class Main
{
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		start();
	
	}

	private static void start()
	{
		System.out.println("==Bank==");
		
		System.out.print("\n1.) Login \n2.) Register\n\nEnter a number to continue: ");
		try {
			
			int mainChoice = Integer.parseInt(input.nextLine());
			
			switch(mainChoice)
			{
				case 1:
					System.out.println("==Login==");
					LoginController.loginUser();
					break;
				case 2:
					System.out.print("\n==Register==");
					
					User user = LoginController.registerUser();
					LoginController.loginUser(user);
					
					break;
				default:
					System.out.println("Incorrect input.  Try again.");
					start();
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Incorrect input.  Try again.");
			start();
		}
		
		
	}
}
