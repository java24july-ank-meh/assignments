package com.revature.hw;

public class Main {
	public static void main(String[] args) {
		//fibonacci();
		//System.out.println(nFact(7));
	}
	
	public static void fibonacci() {
		System.out.println(0 + "\n" + 1);
		fibonacci(0,1,3);
	}
	
	private static void fibonacci(int a, int b, int number) {
		System.out.println(a+b);
		if(number == 25)
			return;
		fibonacci(b,(a+b),number+1);
	}
	
	public static int nFact(int n) {
		return nFact(n,1);
	}
	private static int nFact(int n, int fact) {
		if(n == 1)
			return 1;
		fact*=n*nFact(n-1,fact);
		return fact;
	}
}
/*
 * 1) Write a method to print the first 25 number in the fibonacci
 *    sequence including zero
 * 2) Write a method to compute n!
 */