package com.revature.codechallenge;

public class Fibonacci {
	public static void main(String[] args) {
		int f1 = 0;
		int f2 = 1;
		int temp = 1;
		int nums = 25;
		
		for (int i = 0; i < nums; i++) {
			System.out.println(f1);
			
			temp = f1 + f2;
			f1 = f2;
			f2 = temp;
		}
	}
}
