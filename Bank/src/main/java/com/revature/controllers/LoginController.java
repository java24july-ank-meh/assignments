package com.revature.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import com.revature.dao.UserDAOImp;
import com.revature.models.User;

public class LoginController
{
	private static Scanner input = new Scanner(System.in);

	public static User registerUser()
	{
		System.out.print("\nUsername: ");
		String username = input.nextLine();

		System.out.print("Password: ");
		String password = input.nextLine();

		System.out.print("Confirm Password: ");
		String confirmPassword = input.nextLine();

		if (!password.equals(confirmPassword))
		{
			System.out.println("Passwords don't match. Try again");
			registerUser();
		}

		User user = new User(username, password);

		// check if username exists

		UserDAOImp database = new UserDAOImp();
		User TryUser = database.readUser(user);

		if (TryUser == null)
		{
			database.createUser(user);
			System.out.printf("\nUser %s successfuly created.\n", user.getUsername());

		} else
		{
			System.out.println("\nUser already exists. Select differnt username");
			registerUser();
		}

		return user;
	}

	public static void loginUser()
	{
		// Get Input

		System.out.print("Username: ");
		String username = input.nextLine();

		System.out.print("Password: ");
		String password = input.nextLine();

		User user = new User(username, password);

		loginUser(user);
	}

	public static void loginUser(User user)
	{
		Properties properties = new Properties();

		InputStream inputStream = null;

		try
		{
			inputStream = new FileInputStream("connection.properties");
		} catch (FileNotFoundException e1)
		{

			e1.printStackTrace();
		}

		try
		{
			properties.load(inputStream);
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

		if (user.getUsername().equals(properties.getProperty("superusername"))
				&& user.getPassword().equals(properties.getProperty("superpassword")))
		{
			System.out.println("\nUser logged in\n");

			SuperUserMenuController.start();
		} else
		{
			UserDAOImp database = new UserDAOImp();

			User returnedUser = database.readUser(user);

			try
			{
				if (user.getUsername().equals(returnedUser.getUsername())
						&& user.getPassword().equals(returnedUser.getPassword()))
					UserMenuController.start(returnedUser);
				else
				{
					System.out.println("Incorrect username or password. ");
					loginUser();
				}
			} catch (NullPointerException e)
			{
				System.out.println("Incorrect username or password. ");
				loginUser();
			}

		}

	}
}
