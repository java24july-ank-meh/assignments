package com.revature.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.dao.UserDAOImp;
import com.revature.main.Main;
import com.revature.models.User;

/**
 * 1 - View All Users 2 - Create User 3 - Update User 4 - Delete All Users
 *
 */
public class SuperUserMenuController
{
	private static Scanner input = new Scanner(System.in);

	public static void start()
	{

		System.out.println("==Super User Menu==");
		System.out.println("1) View All Users \n2) Create User \n3) Update User \n4) Delete All Users \n5) Logout");
		System.out.print("\nEnter an option to continue: ");

		int choice = Integer.parseInt(input.nextLine());

		switch (choice)
		{
		case 1:
			viewAllUsers();
			break;
		case 2:
			createUser();
			break;
		case 3:
			updateUser();
			break;
		case 4:
			deleteAllUsers();
			break;
		case 5:
			Main.main(null);
		default:
			System.out.println("Incorrect option. Try again");
			start();
		}
	}

	public static void updateUser()
	{
		System.out.println("==Update User==");

		System.out.print("Enter username: ");
		String username = input.nextLine();

		UserDAOImp database = new UserDAOImp();

		try
		{
			User user = database.readUser(new User(username, ""));

			System.out.printf("\nSelected %s\n", user.getUsername());

			System.out.print("Enter new username: ");
			username = input.nextLine();

			System.out.print("Enter new password: ");
			String password = input.nextLine();

			try
			{
				user = database.updateUser(user, new User(username, password));
			} catch (NullPointerException e)
			{
				System.out.println("Error");
			}

			start();
		} catch (NullPointerException e)
		{
			System.out.println("User not found");
			updateUser();
		}

	}

	public static void viewAllUsers()
	{
		System.out.println("==All Users==");
		
		// get users
		UserDAOImp database = new UserDAOImp();
		ArrayList<User> users = database.readAllUsers();

		for (User user : users)
			System.out.println(user);

		System.out.println("");
		start();
	}

	public static void createUser()
	{
		System.out.println("==Create User==");

		// create user
		LoginController.registerUser();

		start();
	}

	public static void deleteAllUsers()
	{
		System.out.println("==Delete All Users==");
		// get users
		System.out.println("Are you sure you want to delete all users?  [y]es or [n]o");
		if (input.nextLine().toLowerCase().equals("y"))
		{
			UserDAOImp database = new UserDAOImp();
			database.deleteAllUsers();
			start();
		} else
			start();
	}

}
