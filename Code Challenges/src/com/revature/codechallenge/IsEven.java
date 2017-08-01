package com.revature.codechallenge;

public class IsEven {

	public static void main(String[] args) {
		System.out.println(isEven(6));
		System.out.println(isEven(9));
		
	}
	
	public static boolean isEven(int i) {
		if (i/2 == (i+1)/2)
			return true;
		return false;
	}

}
