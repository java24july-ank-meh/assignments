package com.Revature.strings;

import java.util.Stack;

public class StringOps {
	private String original;
	private String reversed = "";
	private String substring = "";
	public StringOps(String entry){
		original = entry;
	}
	
	public String reverse(){
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
	public String substring(int a){
		substring = "";
		int x = original.length();
		for (int i=a; i<x; i++){
			substring = substring + original.charAt(i);
		}
		
		return substring;
	}
}
