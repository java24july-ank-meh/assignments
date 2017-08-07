package com.banking.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.banking.dao.BankDao;
import com.banking.dao.BankDaoImpl;
import com.banking.domain.Account;
import com.banking.domain.User;
import com.banking.exception.OverDraftException;

public class Main {

	public static void main(String[] args) throws IOException, OverDraftException {
		BankDao dao = new BankDaoImpl();
		List<Account> accounts = new ArrayList<Account>();
		List<User> users = new ArrayList<User>();
		User user = new User();
		Scanner sc = new Scanner(System.in);
		double d;
		int superuser, userId;
		String s, username, password;
		boolean cont = true;

		System.out.println("Welcome to Jackson's Bank!!");
		System.out.println("To sign in type S or type R to register an account.");
		s = sc.nextLine();
		while (cont) {
			if (s.equals("S")) {
				System.out.println("Please enter your username.");
				username = sc.nextLine();
				System.out.println("Please enter your password.");
				password = sc.nextLine();
				user = dao.login(username, password);
			} else if (s.equals("R")) {
				System.out.println("Please enter your desired username.");
				username = sc.nextLine();
				System.out.println("Please enter your desired password.");
				password = sc.nextLine();
				dao.createUser(username, password, 0);
				System.out.println("Account created");
				user = dao.login(username, password);

			} else {
				System.out.println("Please enter a valid command!");
				System.out.println("To sign in type S or type R to register an account.");
				s = sc.nextLine();
			}

			if (user.isSuperuser() != -1) {
				System.out.println("You are logged in user: " + user.getUsername());
				cont = false;
			}
		}
		cont = true;
		while (cont) {
			System.out.println();
			System.out.println("Please enter a command: CREATE ACCOUNT(A), DELETE ACCOUNT(DA),"
					+ " DEPOSIT(D), LOGOUT(L), VIEW ACCOUNTS(V), WITHDRAW(W)");
			if (user.isSuperuser() == 1)
				System.out.println(
						"Additional commands: CREATE USER(C), DELETE USER(DU), UPDATE USER(U), VIEW USERS(VU)");
			s = sc.nextLine();
			if (s.equals("CREATE ACCOUNT") || s.equals("A")) {
				System.out.println("Please input the desired name for your new account.");
				s = sc.nextLine();
				dao.createAccount(new Account(0, user.getUserId(), s, 0));

			} else if (s.equals("DELETE ACCOUNT") || s.equals("DA")) {
				System.out.println("Please input the name of the account you wish to delete.");
				s = sc.nextLine();
				dao.deleteAccount(user.getUserId(), s);
			} else if (s.equals("DEPOSIT") || s.equals("D")) {
				System.out.println("Please input the name of the account you want to use for a deposit.");
				s = sc.nextLine();
				System.out.println("Please input the desired amount you want to deposit.");
				d = sc.nextDouble();
				sc.nextLine();
				dao.depositAmount(user.getUserId(), s, d);
			} else if (s.equals("VIEW ACCOUNTS") || s.equals("V")) {
				accounts = dao.viewAllAccounts(user);
				for (Account a : accounts)
					System.out.println(a.toString());
			} else if (s.equals("WITHDRAW") || s.equals("W")) {
				System.out.println("Please input the name of the account you want to use for a withdraw.");
				s = sc.nextLine();
				System.out.println("Please input the desired amount you want to withdraw.");
				d = sc.nextDouble();
				sc.nextLine();
				dao.withdrawAmount(user.getUserId(), s, d);
			} else if (s.equals("CREATE USER") || s.equals("C") && user.isSuperuser() == 1) {
				System.out.println("Please input the name of the user you want to create.");
				username = sc.nextLine();
				System.out.println("Please input the password of the user you want to create.");
				password = sc.nextLine();
				System.out.println(
						"Please input 'S' if you want the user to be a superuser or press an key to create a regular user.");
				superuser = 0;
				s = sc.nextLine();
				if (s.equals("S"))
					superuser = 1;
				dao.createUser(username, password, superuser);
			} else if (s.equals("DELETE USER") || s.equals("DU") && user.isSuperuser() == 1) {
				System.out.println("Give the user id of the user you want to delete");
				superuser = sc.nextInt();
				sc.nextLine();
				dao.deleteUser(superuser);
			} else if (s.equals("UPDATE USER") || s.equals("U") && user.isSuperuser() == 1) {
				System.out.println("Give the user id of the user you want to update");
				userId = sc.nextInt();
				sc.nextLine();
				System.out.println("Please input the new name of the user.");
				username = sc.nextLine();
				System.out.println("Please input the new password of the user.");
				password = sc.nextLine();
				System.out.println(
						"Please input 'S' if you want the user to be a superuser or press an key to create a regular user.");
				superuser = 0;
				s = sc.nextLine();
				if (s.equals("S"))
					superuser = 1;
				dao.updateUser(userId, username, password, superuser);
			} else if (s.equals("VIEW USERS") || s.equals("VU") && user.isSuperuser() == 1) {
				users = dao.viewUsers();
				for (User u : users)
					System.out.println(u.toString());
			} else if (s.equals("LOGOUT") || s.equals("L")) {
				cont = false;
			} else {
				System.out.println("Invalid command");
			}
		}
		sc.close();
		System.out.println("Logging out...Thanks for your money");
	}
}