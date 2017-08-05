package com.homework.Q5;

public class Q5 {

	public static void main(String[] args) {
		//example
		System.out.println(subString("Hello", 3));
	}

	public static String subString(String s, int index) {
		StringBuilder sb = new StringBuilder();
		//loop through appending characters to a stringbuilder from the given string
		for(int i = 0; i <= index-1; ++i) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

}
