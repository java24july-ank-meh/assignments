package com.revature.challenge;

public class MathStuff {
	
	public void fibonacci(int n) { // calculates nth fibonnacci number;
		
		int[] fib = new int[n+1];
		fib[0] = 0;
		System.out.println(fib[0]);
		if (n >= 2) {
			fib[1] = 1;
			System.out.println(fib[1]);
			if (n > 2) {
				for (int i = 2; i < fib.length; i++) {
					fib[i] = fib[i - 1] + fib[i - 2];
					System.out.println(fib[i]);
				}
			}
		}
	}

	public void factorial(int n) {
		int x = 0;
		for (int i = 0; i <= n; i++) {
			x += i;
		}
		System.out.println(x);
	}

}
