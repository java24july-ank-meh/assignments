package Q17;

import java.util.Scanner;

public class Question17 {
	
	/*
	 * Q17 Write a program that calculates the simple interest on the principal, rate of interest, and number of years provide by the user.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter principal amount: ");
		double prin=sc.nextDouble();
		System.out.print("Enter rate of interest: ");
		double interest=sc.nextDouble();
		System.out.print("Enter time (in months): ");
		int time=sc.nextInt();
		System.out.println("After " + time + " months with " + interest + "% interest rate, your principal amount will grow from $" + prin + " to $" + prin*interest*time);

	}

}
