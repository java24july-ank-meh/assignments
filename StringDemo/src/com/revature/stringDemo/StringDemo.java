package com.revature.stringDemo;

import java.util.Arrays;

public class StringDemo {

	public static void main(String[] args) {
		String str1 = "Hello world";
		String str2 = new String("HELLO WORLD");
		/*
		if(str1.equalsIgnoreCase(str2)) {
			System.out.println("Equals");
		}else {
			System.out.println("Not Equal");
		}}*/
		
		char[] helloArray = {'h','e','l','l','o'};
		String helloString = new String (helloArray);
		System.out.println(helloString);
		
		//indexOf
		String str3 = "The quick brown fox jumps over the lazy dog";
		System.out.println(str3.indexOf('k'));
		str3 = "";
		str3 = null;
		
		//isEmpty
		//System.out.println(str3.isEmpty());
		
		//format
		String s = "hello";
		s = String.format("%s world", s);
		System.out.println(s);
		
		//split
		String s2 = "Hello world everyone";
		String[] returned = s2.split(" ");
		System.out.println(Arrays.toString(returned));
		
		String s4 = "aloha";
		char[] arr = new char[5];
		s4.getChars(0, 5, arr, 0);
		System.out.println(arr);
	
		//replace
		String s5 = "hola";
		String s6 = s5.replace('o', 'u');
		System.out.println(s6);
		
		//String Buffer
		StringBuffer sb = new StringBuffer("doom");
		sb.reverse();
		System.out.println(sb);
		varargMethod(0,0,returned);
	}
	public static void varargMethod(int x, int y, String... strings) {
		System.out.println(Arrays.toString(strings));
	}
}

/*
 * 1) reverse a string without using a temp variable. Do not use reverse()
 * 2) Write a substring method that accepts a string str and an integer idx
 * 		and returns a substring between 0 and idx-1 inclusive. Do Not use
 * 		any existing substring methods in String, ...
 */

