package com.revature.Q4;

public class Q4 {
	
	public static void factorial(int n) {
		int x = 0;
		for (int i = 0; i <= n; i++) {
			x += i;
		}
		System.out.println(x);
	}

	public static void main(String[] args) {
		factorial(25);

	}

}
