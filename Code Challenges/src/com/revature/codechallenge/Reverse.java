package com.revature.codechallenge;

public class Reverse {
	public static void main(String[] args) {
		String str = "This string will be reversed";
		char[] reverse = new char[str.length()];

		for (int i = str.length() - 1; i >= 0; --i) {
			int pos = str.length() - 1 - i;
			reverse[pos] = str.charAt(i);
		}
		str = new String(reverse);
		System.out.println(str);
	}
}
