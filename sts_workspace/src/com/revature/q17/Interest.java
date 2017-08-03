package com.revature.q17;

import java.util.Scanner;

public class Interest {
	
	public static void main(String[] args){
		System.out.println(calculateInterest(4));
	}

	public static double calculateInterest(int years) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter principal");
		double principal = s.nextDouble();
		System.out.println("Enter rate");
		double rate = s.nextDouble();
		return principal*rate*years;
		
	}
}
