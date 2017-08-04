package com.revature.ClassDemo;

public class ReverseStringDemo {

	public static void main(String[] args) {
			
		String myMood = "mood";
		
		System.out.println(myMood);
		myReverse(myMood);
//		System.out.println(mySecondReverse(myMood));
		

	}

	private static void myReverse(String str) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = str.length()-1; i>=0; i--) {
			sb.append(str.charAt(i));
		}
		System.out.println(sb);
	}
	
//	private static String mySecondReverse(String str) {
//
//		String newRan = "";
//		
//		for(int i =str.length()-1; i>=0; i--) {
//			newRan += str.charAt(i);
//		}
//		return newRan;
//		
//	}
}
