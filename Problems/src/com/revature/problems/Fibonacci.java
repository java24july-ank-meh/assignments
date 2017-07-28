package com.revature.problems;

public class Fibonacci {
	public void fib() {
		int num1 = 0;
		int num2 = 1;
		int num3 = 1;
		System.out.print("0" + " 1 ");
		for (int i = 0; i < 23; ++i) {
			num3 = num1 + num2;
			num1 = num2;
			num2 = num3;
			System.out.print(num3 + " ");
		}
	}
}