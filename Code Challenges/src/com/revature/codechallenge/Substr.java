package com.revature.codechallenge;

public class Substr {
	public static void main(String[] args) {
		String str = "this is a string";

		System.out.println(makeSubstr(str, 6));
	}

	public static String makeSubstr(String str, int idx) {
		char[] substr = new char[idx];

		for (int i = 0; i < idx; ++i) {
			substr[i] = str.charAt(i);
		}

		return new String(substr);
	}

}
