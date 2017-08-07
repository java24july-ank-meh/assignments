package com.revature.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.dao.AccountDAOImpl;
import com.revature.dao.UserDAOImp;
import com.revature.main.Main;
import com.revature.models.Account;
import com.revature.models.User;

public class UserMenuController
{
	private static Scanner input = new Scanner(System.in);
	private static User user = null;

	public static void start()
	{

		System.out.println("==User Menu==");
		System.out.println(
				"1) View Accounts \n2) Create New Account \n3) Delete Account \n4) Deposit \n5) Withdraw \n6) logout");
		System.out.print("\nEnter an option to continue: ");

		int choice = Integer.parseInt(input.nextLine());

		switch (choice)
		{
		case 1:
			viewAccounts();
			break;
		case 2:
			createAccount();
			break;
		case 3:
			deleteAccount();
			break;
		case 4:
			deposit();
			break;
		case 5:
			withdraw();
			break;
		case 6:
			Main.main(null);
		default:
			System.out.println("Incorrect option. Try again");
			start();

		}
	}

	public static void start(User user)
	{

		UserMenuController.user = user;
		start();
	}

	public static void viewAccounts()
	{
		System.out.println("==User Accounts==");

		AccountDAOImpl accountDatabase = new AccountDAOImpl();

		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = accountDatabase.readAllAccounts(user.getUserID());

		for (Account account : accounts)
			System.out.println(account);

		System.out.println();
		start();

	}

	public static void createAccount()
	{
		System.out.println("==New Account==");
		System.out.print("Enter Account name: ");
		String name = input.nextLine();

		System.out.print("Enter amount to deposit: ");
		double amount = Double.parseDouble(input.nextLine().replaceAll(",", ""));

		Account account = new Account(name, amount);
		AccountDAOImpl accountDatabase = new AccountDAOImpl();

		accountDatabase.createAccount(account, user.getUserID());

		System.out.printf("\nAccount %s created successfully.\n", account.getName());
		System.out.println(account);
		start();

	}

	public static void deleteAccount()
	{
		System.out.println("== Close Account ==");

		AccountDAOImpl accountDatabase = new AccountDAOImpl();

		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = accountDatabase.readAllAccounts(user.getUserID());

		for (int i = 0; i < accounts.size(); i++)
		{
			System.out.printf("\n%d.) %s", i + 1, accounts.get(i));
		}

		System.out.print("\nSelect account to delete: ");
		int choice = Integer.parseInt(input.nextLine());

		try
		{
			if (accounts.get(choice - 1).getBalance() == 0)
			{
				accountDatabase.deleteAccount(accounts.get(choice - 1));
				System.out.println("Account deleted");
			} else
			{
				System.out.println("ERROR.  Account needs to be emptied first");
			}

		} catch (IndexOutOfBoundsException e)
		{
			System.out.println("Invalid account type. ");
		}

		start();

	}

	public static void deposit()
	{
		System.out.println("\n==Deposit==");

		AccountDAOImpl accountDatabase = new AccountDAOImpl();

		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = accountDatabase.readAllAccounts(user.getUserID());

		for (int i = 0; i < accounts.size(); i++)
		{
			System.out.printf("\n%d.) %s", i + 1, accounts.get(i));
		}

		System.out.print("\nSelect account to deposit: ");
		int choice = Integer.parseInt(input.nextLine());

		System.out.print("Enter amount to deposit: ");
		double amount = Double.parseDouble(input.nextLine().replaceAll(",", ""));

		try
		{
			accountDatabase.deposit(accounts.get(choice - 1), amount);
		} catch (IndexOutOfBoundsException e)
		{
			System.out.println("Invalid account type. ");
		}

		start();

	}

	public static void withdraw()
	{
		System.out.println("\n==Withdraw==");

		AccountDAOImpl accountDatabase = new AccountDAOImpl();

		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = accountDatabase.readAllAccounts(user.getUserID());

		for (int i = 0; i < accounts.size(); i++)
		{
			System.out.printf("\n%d.) %s", i + 1, accounts.get(i));
		}

		System.out.print("\nSelect account to withdraw: ");
		int choice = Integer.parseInt(input.nextLine());

		System.out.print("Enter amount to withdraw: ");
		double amount = Double.parseDouble(input.nextLine().replaceAll(",", ""));

		try
		{
			accountDatabase.withdraw(accounts.get(choice - 1), amount);
		} catch (IndexOutOfBoundsException e)
		{
			System.out.println("Enter a valid account.");
		}

		start();

	}

}
