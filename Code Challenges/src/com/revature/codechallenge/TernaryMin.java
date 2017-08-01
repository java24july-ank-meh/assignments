package com.revature.codechallenge;

public class TernaryMin {
	public static void main(String[] args) {
		System.out.println(ternaryMin(5, 2));
	}
	
	public static int ternaryMin(int a, int b) {
		return a < b ?  a :  b;
	}
}
