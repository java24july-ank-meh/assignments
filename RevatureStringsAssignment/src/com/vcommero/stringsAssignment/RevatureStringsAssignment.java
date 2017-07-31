package com.vcommero.stringsAssignment;

public class RevatureStringsAssignment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str1 = "I am a string.";
		System.out.println(function1(str1));

		String str2 = "Look! I am also a string!";
		System.out.println(function2(str2, 20));
	}

	public static String function1 (String str) {
		String result = "";
		for (int i=str.length()-1; i>=0; i--) {
			result += Character.toString(str.charAt(i));
		}
		return result;
	}
	
	public static String function2 (String str, int idx) {
		String result = "";
		for (int i=0; i<idx; i++) {
			result += Character.toString(str.charAt(i));
		}
		return result;
	}
}
