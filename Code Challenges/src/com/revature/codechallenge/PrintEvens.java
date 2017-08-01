package com.revature.codechallenge;


public class PrintEvens {
	public static void main(String[] args) {
		int[] evens = new int[100];
		
		for (int i = 0; i < 100; ++i) {
			evens[i] = i+1;
		}
		
		for (int i : evens) {
			if (i % 2 == 0)
				System.out.println(i);
		}
	}
}
