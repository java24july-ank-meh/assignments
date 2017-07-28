package com.revature.assignment1;

import java.util.Stack;


public class ReverseString {

	/**
	* Returns a reversed version of a String using a stack to push each character
	* and then using pop to append to a StringBuilder.  
	* 
	* @param a string text that is to be reversed.
	* @return a new string with the original text reversed.		
	*
	*/
	public static String reverseString(String string) 
	{
		String str = string;
		
		Stack<Character> stack = new Stack<Character>();
		
		StringBuilder sb = new StringBuilder();
		
		for(char i: str.toCharArray())
			stack.push(i);
		
		while(!stack.isEmpty())
			sb.append(stack.pop());
		
		return sb.toString();
	}
	

	public static void main(String[] args)
	{
		System.out.println( reverseString("Hello World") );
		
		System.out.println( reverseString("This is another String") );
	}

}
