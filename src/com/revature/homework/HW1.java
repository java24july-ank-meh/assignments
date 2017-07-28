package com.revature.homework;
public class HW1 {

	public static void main(String[] args) {
		System.out.println(reverseString("Hello World"));
		System.out.println(substring("Hello World",8));
	}
	
	public static String reverseString(String str) {
		StringBuilder sb = new StringBuilder();
		for(int i = str.length()-1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	
	public static String substring(String str, int idx) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < idx; i++) {
			sb.append(str.charAt(i));
		}
		
		return sb.toString();
	}
	

}
