package com.homework.Q3;

public class Q3 {

	public static void main(String[] args) {

		//given a string s
		String s = "Hello";
		StringBuilder sb = new StringBuilder();
		
		//append characters to a stringbuilder from the back of the string for the length of the string
		for(int i = 0; i < s.length(); ++i) {
			sb.append(s.charAt(s.length()-i-1));
		}
		System.out.println(sb.toString());
	}

}
