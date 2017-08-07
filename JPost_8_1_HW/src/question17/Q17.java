package question17;

import java.util.Scanner;

public class Q17 {
	/*
	 * Question 17: Write a program that calculates the simple interest
	 * on the principal, rate of interest and number of years provided
	 * by the user. Enter principal, rate and time through the console
	 * using the Scanner class.
	 * 				Interest = Principal*Rate*Time
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Principal amount: ");
		int p = sc.nextInt();
		System.out.print("\nEnter Rate of interest: ");
		int r = sc.nextInt();
		System.out.print("\nEnter Number of years: ");
		int t = sc.nextInt();
		sc.close();
		int interest = p*r*t;
		System.out.println("\nInterest = " + interest);
	}
}
