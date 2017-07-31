package com.revature.challenge;

public class StringStuff {

	public static String reverseString(String x) {
		String z = new String(); // new string to hold the revers x. not sure if this counts as a temporary var
									// or not...

		for (int i = x.length() - 1; i >= 0; i--) {
			z += x.charAt(i); // adds each char starting from end of string x
		}
		return z;
	}

	public static String subString(String x, int y) {
		String z = new String(); // new string to become the substring

		for (int i = 0; i < y; i++) {
			z += x.charAt(i); // forloop that cycles through the chars of x up to index y adds them to z;
		}
		return z;
	}

}
