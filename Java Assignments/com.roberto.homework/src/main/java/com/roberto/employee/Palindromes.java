package com.roberto.employee;

import java.util.*;

public class Palindromes {
	
	/*
	 * ArrayList, contrary to Vector, is unsynchronized which means it is faster but not thread safe
	 * and contrary to java Arrays, ArrayList can change size where as arrays are a fixed size. 
	 */
	
	
	public static void main(String[] args) {
		ArrayList<String> arrList1 = createArrays();
		ArrayList<String> arrList2 = getPalindromes(arrList1);
		
		if(arrList2.isEmpty()) {
			System.out.println("The list is empty");
		}
		else {
			for(String s : arrList2) {
				System.out.println(s);
			}
		}
		
	}

	public static ArrayList<String> createArrays() {
		ArrayList<String> inputString = new ArrayList<String>();
		
		
		inputString.add("karan");
		inputString.add("madam");
		inputString.add("tom");
		inputString.add("civic");
		inputString.add("radar");
		inputString.add("sexes");
		inputString.add("jimmy");
		inputString.add("kayak");
		inputString.add("john");
		inputString.add("refer");
		inputString.add("billy");
		inputString.add("did");
		
		return inputString;
		
	}
	
	public static ArrayList<String> getPalindromes(ArrayList<String> inStr) {
		
		ArrayList<String> pali = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for(String s : inStr) {
			for(int i = s.length() - 1; i >= 0; i--) {
				sb.append(s.charAt(i));
				
			}
			
			if(sb.toString().equals(s)) pali.add(s);
			sb.setLength(0);
			
		}
		
		return pali;
	}
	
}
