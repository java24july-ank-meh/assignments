package com.revature.Q5;

public class Q5 {
	public static String subString(String x, int y) {
		String z = new String(); // new string to become the substring

		for (int i = 0; i < y; i++) {
			z += x.charAt(i); // forloop that cycles through the chars of x up to index y adds them to z;
		}
		return z;
	}

	public static void main(String[] args) {
		subString("there once was a man", 10);
	}

}
