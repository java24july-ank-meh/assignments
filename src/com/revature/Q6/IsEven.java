package com.revature.Q6;

public class IsEven {
	public static void main(String[] args) {
		int num = 24;
		if(isEven(num)) {
			System.out.print(num + " is even");
		} else {
			System.out.print(num + " is odd");
		}
	}
	
	static boolean isEven(int num) {
	    return (num & 1) == 0;
	}
}
