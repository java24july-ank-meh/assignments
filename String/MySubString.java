package com.revature.stringassignment;

import java.util.ArrayList;

public class MySubString {

	/*
	 * (1) Reverse string without using a temporary variable. Do NOT use reverse(); 
	 * 		-find string' length and switch pairs starting with beginning and end
	 * 
	 * (2)  Write a SubString method that accepts a String str and an integer idx
	 * 		and returns the substring contained between 0 and idx-1. Do NOT use any
	 * 		existing substring methods in String;
	 * 		-
	 */

	public static void main(String[] args) {

		MySubString ss = new MySubString();


		String s1 = "reverse";
		System.out.println("String before reverse: "+s1);
		System.out.println("String after reverse1: "+ss.reverseNew1(s1));
		System.out.println("String after reverse2: "+ss.reverseNew2(s1));

		String sr1 = new StringBuilder(s1).reverse().toString();
		System.out.println("String reversed by StringBuilder: "+sr1);
		
		String s2 = "computer";
		int idx = 4;
		System.out.println("String before shortening it: "+s2);
		System.out.println("String after shortening(1) it to "+idx+": "+ss.returningLess1(s2,4));
		System.out.println("String after shortening(2) it to "+idx+": "+ss.returningLess2(s2,4));
		
		String ss2 = new StringBuilder(s2).subSequence(0,idx).toString();
		System.out.println("String after StringBuilder shortened it: "+ss2);

	}
	//with char array
	public String reverseNew1(String s) {
		int size = s.length();

		char[] charsA = s.toCharArray();
		ArrayList<Character> charsAL = new ArrayList<Character>();

		for(int i=size-1;i>=0;i--) {
			charsAL.add(charsA[i]);
//			System.out.println(charsA[i]);
		}
		
		s="";
		
		for(char c: charsAL) {
//			System.out.println(c);
//			System.out.println(s);
			s = s.concat(""+c);
//			System.out.println(s);
		}

		return s;
	}
	
	public String reverseNew2(String s) {
		int size = s.length();
		char[] charsA = s.toCharArray();
		
		s="";
		
		for(int i=size-1;i>=0;i--) {
//			System.out.println(charsA[i]);
			s = s.concat(""+charsA[i]);	//or s += charsA[i];
		}
		
		return s;
	}
	
	//with stringbuilder
	public String reverseNew3(String s) {
		StringBuilder sb = new StringBuilder();
		
		int size = s.length();
		
		for(int i=size-1;i>=0;i--) {
			sb.append(s.charAt(i));			
		}
		
		
		s = sb.toString();
		return s;
	}
	
	
	//-------------------------------------------------------------

	public String returningLess1(String s, Integer idx) {
		char[] charsA = s.toCharArray();
		
		s = "";
		
		for(int i=0;i<=idx-1;i++) {
			s = s.concat(""+charsA[i]);	//or s += charsA[i];
		}

		return s;
	}
	
	public String returningLess2(String s, Integer idx) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<=idx-1;i++) {
			sb.append(s.charAt(i));
		}
		
		s = sb.toString();
		return s;
	}

}
