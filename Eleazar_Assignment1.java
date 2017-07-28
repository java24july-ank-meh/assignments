// Written by Eleazar Rosales
package com.Revature.Assignment1;

public class Assignment1 {
	
	public static void main(String[] args) {
		System.out.println(FindMe("Hello, my name is Eleazar Rosales.", 6));
		System.out.println(Rewind("Hello, my name is Eleazar Rosales."));
	}
	
	public static String Rewind(String str){
		StringBuilder result = new StringBuilder();
		for(int i = str.length()-1; i >= 0; i--){
			result.append(str.charAt(i));
		}
		return result.toString();
	}
	
	public static String FindMe(String str, int idx){
		StringBuilder result = new StringBuilder();
		String[] ss = str.split("");
		for(int i = 0; i <= idx; i++){
			result.append(ss[i]);
		}
		return result.toString();
	}
	
}
