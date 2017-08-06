package com.revature.assignments;

import java.util.Scanner;

public class Question17 {

	public static void main(String[] args) {
		CalculateInterest();
	}
	
	public static void CalculateInterest() {
		Scanner sc = new Scanner(System.in);
		double principal, rate, interest;
		int time;
		System.out.print("Enter the principal amount: ");
		principal = sc.nextDouble();
		System.out.print("Enter the rate of interest: ");
		rate = sc.nextDouble();
		System.out.print("Enter the number of years ");
		time = sc.nextInt();
		interest = principal * rate * time;
		System.out.println("Your interest on the principal is: "+ interest);
	}
}
