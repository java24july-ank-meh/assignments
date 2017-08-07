package com.revature.homework;

public class StringsHW {
	public static void main (String[] args) {
		System.out.println(reverseString("Hello"));
		System.out.println(subString("Hello",4));
	}
	static String reverseString(String str) {
		StringBuilder s = new StringBuilder();
		for(int x = str.length()-1; x >= 0; x--)
			s.append(str.charAt(x));
		return s.toString();
	}
	static String subString(String str,int idx) {
		StringBuilder s = new StringBuilder();
		for(int x = 0; x < idx; x++)
			s.append(str.charAt(x));
		return s.toString();
	}
}


/*
 * 1) reverse a string without using a temp variable. Do not use reverse()
 * 2) Write a substring method that accepts a string str and an integer idx
 * 		and returns a substring between 0 and idx-1 inclusive. Do Not use
 * 		any existing substring methods in String, ...
 */