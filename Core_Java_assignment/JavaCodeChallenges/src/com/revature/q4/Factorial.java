package com.revature.q4;

public class Factorial {
	public static int fact(int n){
		if(n == 0) return 1;
		return n * fact(n-1);
	}
}
