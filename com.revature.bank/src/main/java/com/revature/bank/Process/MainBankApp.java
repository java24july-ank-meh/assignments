package com.revature.bank.Process;

import com.revature.bank.DAO.*;

public class MainBankApp {

	public static void main(String[] args) {
		DatabaseFunction d = new DatabaseFunction();
		
		d.startUpLogo();
		d.loginMenu();
	}

}
