package com.revature.assignment1;


public class NewSubString {
	
	
	/**
	* Returns a substring of a given string.
	* 
	* @param str string to be used as source.
	* @param int number of characters that will be returned.
	*
	*/
	public static String NewSubString(String str, int idx) {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < idx; i++)
			sb.append(str.charAt(i));
			
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println( NewSubString("Hello World", 3));
	}
}
