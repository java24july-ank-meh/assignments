package com.revature.problems;

public class Main {

	public static void main(String[] args) {
		new Fibonacci().fib();
		System.out.println();
		System.out.println(new Factorial().factorial(5));


		//reverse string
		String string1 = "jackson";
		char temp = ' ';
		char[] arr = new char[string1.length()];
		arr = string1.toCharArray();
		for(int i = 0; i < string1.length()/2; ++i) {
			temp = arr[i];
			arr[i] = arr[string1.length()-1 - i];
			arr[string1.length()-1 - i] = temp;
		}
		for(int i = 0; i < string1.length();++i)
		string1 = new String(arr);
		System.out.println(string1);
		
		//substring
		System.out.println(subString(string1, 1, 2));
	}
	
	public static String subString(String s, int a, int b) {
		StringBuilder sb = new StringBuilder();
		while(a <=b) {
			sb.append(s.charAt(a));
			++a;
		}
		return sb.toString();
		
	}
}