package com.roberto.homework;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Homework hw = new Homework();
		
		
		
		int choice = startupMenu();
		boolean state = false;
		
		do {
			switch(choice) {
				case 1://bubble sort
					System.out.println(Arrays.toString(hw.bubbleSort()));
					choice = startupMenu();
					break;
					
				case 2://fibonacci
					hw.fibonacci();
					choice = startupMenu();
					break;
					
				case 3://reverse a string
					hw.reverseString();
					choice = startupMenu();
					break;
					
				case 4://compute factorial of a number given by user
					hw.computeNFactorial();
					choice = startupMenu();
					break;
					
				case 5://get a substring for a user input string
					hw.getSubstring();
					choice = startupMenu();
					break;
					
				case 6://decide whether or not a number is even
					hw.isEven();
					choice = startupMenu();
					break;
					
				case 7://give primes from 1-100
					hw.isPrime();
					choice = startupMenu();
					break;
				
				case 8://gets lesser value from two values
					hw.minNum();
					choice = startupMenu();
					break;
				
				case 9:// returns all even numbers from 1 to 100
					hw.evenNum();
					choice = startupMenu();
					break;
				
				case 10:
					hw.printTriangle();
					choice = startupMenu();
					break;
					
				case 11: 
					hw.switchCase();
					choice = startupMenu();
					break;
					
				case 12:
					hw.getInterest();
					choice = startupMenu();
					break;
				
				case 13:
					hw.arrayListMani();
					choice = startupMenu();
					break;
				
				case 14:
					hw.getInfoFile();
					choice = startupMenu();
					break;
					
				case 0://close
					state = true;
					System.out.println("Thanks! Bye!");
					break;
			}
		}while(state == false);
		
	
	}

	
	public static int startupMenu() {
		Scanner in = new Scanner(System.in);
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		System.out.println("Please enter a problem to solve: ");
		System.out.println("1: Bubble sort an array.");
		System.out.println("2: Get 25th numbers of Fibonacci's sequence.");
		System.out.println("3: Reverse a string.");
		System.out.println("4: Compute n factorial.");
		System.out.println("5: Get substring.");
		System.out.println("6: Is this number even?");
		System.out.println("7: Prime numbers between 1-100.");
		System.out.println("8: Get minimum value.");
		System.out.println("9: Get all even numbers betweepn 1-100.");
		System.out.println("10: Print a binary triangle.");
		System.out.println("11: Implement a switch statement.");
		System.out.println("12: Get interest of user input.");
		System.out.println("13: Do some operations with ArrayList.");
		System.out.println("14: Get info from a file.");
		
		System.out.println("0: To close program.");
		
		System.out.println("****************************************************");
		System.out.println("****************************************************");
		
		int choice = in.nextInt();
		
		return choice;
	}
}
