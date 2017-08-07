package com.revature.main;

import com.revature.bankTables.*;
import com.revature.dao.BankDao;
import com.revature.implement.BankDAOImpl;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		BankDao dao = new BankDAOImpl();
		User admin = new Admin("Julia", "Stark", "Cascade95", "superhero");
		//this is the super user
		//dao.createUser(admin);
		System.out.println(dao.readUser("'Cascade95'", "'superhero'"));
		//System.out.println("what");
		
		User Daniel = new User("Daniel", "Avidan", "NotSoGrump", "arin23");
		dao.createUser(Daniel);
		
		
		//Nora was here
		//I promise I did nothing else besides this
	}

}
