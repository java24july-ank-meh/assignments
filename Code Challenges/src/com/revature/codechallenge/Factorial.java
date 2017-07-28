package com.revature.codechallenge;

public class Factorial {
	public static void main(String[] args) {
		System.out.println(factorial(5));
	}
	
	public static int factorial(int n) {
		int total = 1;
		for (int i = n; i > 0; --i) {
			total *= i;
		}
		return total;
	}
}
