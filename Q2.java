package com.revature.Q2;

public class Q2 {
	
	public static void fibonacci(int n) { // calculates nth fibonacci number;

		int[] fib = new int[n + 1];
		fib[0] = 0;
		System.out.println(fib[0]);
		if (n >= 2) {
			fib[1] = 1;
			System.out.println(fib[1]);
			if (n > 2) {
				for (int i = 2; i < fib.length; i++) {
					fib[i] = fib[i - 1] + fib[i - 2];
					System.out.print(fib[i] + ", ");
				}
			}
		}
	}

	public static void main(String[] args) {
		fibonacci(25);

	}

}
