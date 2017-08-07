package com.revature.superuser;

import java.util.Scanner;

import com.revature.users.User;


@SuppressWarnings("resource")
public class SuperUser extends User{
	SuperUserDao suDao = new SuperUserDaoImpl();
	public SuperUser() {
		super();
	}
	public SuperUser(String username, Integer id) {
		super(username,id);
	}
	
	@Override
	public void menu() {
		System.out.println("\nPlease select a menu option: ");
		System.out.println("(V)iew users.");
		System.out.println("(C)reate a user.");
		System.out.println("(R)emove a user.");
		System.out.println("(U)pdate a user.");
		System.out.println("(L)ogout.");
	}
	@Override
	public void execute(char c) {
		switch(c) {
		case 'V': 	viewUsers();
				  	break;
		case 'C':	createUser();
				  	break;
		case 'R': 	removeUser();
					break;
		case 'U': 	updateUser();
					break;
		default: 	break;
		}
	}
	public void viewUsers() {
		suDao.viewUsers();
	}
	public void createUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter account username: ");
		String username = sc.next();
		System.out.println("Enter account password: ");
		String password = sc.next();
		uDao.addUser(username, password);
	}
	public void removeUser() {
		viewUsers();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter account number to remove: ");
		String user = sc.next();
		if(user.equals("cancel"))
			return;
		suDao.removeUser(Integer.parseInt(user));
	}
	public void updateUser() {
		viewUsers();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter account number to update: ");
		String user = sc.next();
		if(user.equals("cancel"))
			return;
		System.out.println("Enter new username: ");
		String username = sc.next();
		System.out.println("Enter new password: ");
		String password = sc.next();
		suDao.updateUser(Integer.parseInt(user), username, password);
	}
}