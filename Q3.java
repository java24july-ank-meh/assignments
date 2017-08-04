package com.revature.Q3;

public class Q3 {
	public static String reverseString(String x) {
		String z = new String(); // new string to hold the revers x. not sure if this counts as a temporary var
									// or not...

		for (int i = x.length() - 1; i >= 0; i--) {
			z += x.charAt(i); // adds each char starting from end of string x
		}
		return z;
	}

	public static void main(String[] args) {
		reverseString("helloWorld");

	}

}
