package com.Revature.switchchal;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Switchcase {
	public void testswitch(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a case 1-3, 4 to exit...");
		int a = in.nextInt();
		while(a != 4){
			switch(a){
			case(1): 
				System.out.println("Enter Number for Root");
				int c = in.nextInt();
				System.out.println(Math.sqrt(c));
				break;
			case(2): 
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println(dateFormat.format(date));
				break;
			case(3):
				System.out.println("I am learning Core Java");
				break;
			}
			System.out.println("Enter a case 1-3, 4 to exit...");
			a = in.nextInt();		
		}
	}
}
