package javaHomework.Q14;

import java.util.Date;
import java.util.Scanner;

public class SwitchCase {

	public static void main(String[] args) {
		
		int s = 0;
		//make scanner object
		Scanner scan = new Scanner(System.in);
		
		//take in input from user
		while(s!=1 && s!=2 && s!=3) {

			System.out.println("Please enter switch (1,2, or 3): ");
			s = scan.nextInt();
		}
		
		
		String msg ="I am learning Core Java";
		String[] split;

		switch (s) {
		case 1:
			//find square root of num using Math class method
			double num = 0;
			
			System.out.println("Please enter a number: ");
			num = scan.nextDouble();
			
			System.out.println("Square root of " + num + " is: " + Math.sqrt(num));
			break;

		case 2:
			//use Date object from java.util to display date
			Date date = new Date();
			System.out.println(date.toString());
			break;

		case 3:
			//split String at whitespaces and store in string array
			split = msg.split("\\s+");
			System.out.println(split);
			break;

		}
		scan.close();

	}

}
