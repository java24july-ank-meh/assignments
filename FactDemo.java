package com.revature.factdemo;

import java.util.Scanner;
import java.math.*;

public class FactDemo {

	public static void main(String[] args) {

		

		int number;
		int factorial = 1;
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter a number ");
		number = keyboard.nextInt();
		
		System.out.println("Numbers to be multiplied: ");
		for(int i = 1; i <= number; i++) {
			
			factorial = factorial *i;
			
			System.out.print(i + " x ");
		}

		System.out.println("\nTotal:  " + factorial);

	}

}
