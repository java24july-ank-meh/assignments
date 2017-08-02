package com.revature.codechallenge;

import java.util.Scanner;

public class Interest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Principal: ");
		double principal = input.nextDouble();
		System.out.print("Rate: ");
		double rate = input.nextDouble();
		System.out.print("Time: ");
		double time = input.nextDouble();
		input.close();
		System.out.println("Interest: " + (principal*rate*time));
	}
}
