package com.Revature.reverser;

import java.util.Stack;

public class ReverseString {
	private String reversed;
	private String original;
	
	public String reverse(String temp){
		original = temp;
		reversed = "";
		int x = original.length();
		Stack stack = new Stack();
		for(int i=0; i<x; i++)
		{
			stack.push(original.charAt(i));
		}
		for(int i=0; i<x; i++)
		{
			reversed = reversed + stack.pop();
		}
		return reversed;
	}
}
