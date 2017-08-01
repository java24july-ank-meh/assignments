package com.revature.q8;

import java.util.ArrayList;
import java.util.Iterator;

public class Q8 {

	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<>();
		words.add("karan"); words.add("madam"); words.add("tom"); words.add("civic"); 
		words.add("radar"); words.add("sexes"); words.add("jimmy"); words.add("kayak");
		words.add("john"); words.add("refer"); words.add("billy"); words.add("did");
		
		ArrayList<String> result = findPalindromes(words);
		
		for(String s : result) {System.out.println(s);}
		
	}
	
	public static ArrayList<String> findPalindromes(ArrayList<String> superset){
		
		ArrayList<String> result = new ArrayList<>();
		
		Iterator<String> it = superset.iterator();
		while(it.hasNext()) {
			String next = it.next();
			if(isPalindrome(next)) {result.add(next);}
		}
		
		return result;
	}
	
	public static boolean isPalindrome(String str) {
		/*
		 * determine if input is a palindrome by reading string "first-to-middle" and 
		 * "end-to-middle." If these strings are equivalent, the input is a palindrome. 
		 */
		
		boolean evenLength = str.length() % 2 == 0; 
		
		StringBuilder first = new StringBuilder();
		StringBuilder second = new StringBuilder();
		/*
		 * If input has even length, there is no middle character. first string is 0 to 
		 * str.length()/2 -1 and second is str.length() to str.length()/2. If length is odd, 
		 * both strings include the middle character.
		 */
		int halfway = str.length()/2;
		if(evenLength) {halfway -= 1;}
		
		for(int i=0; i<=halfway; i++) {
			first.append(str.charAt(i));
		}
		
		if(evenLength) {halfway += 1;}
		
		for(int i=str.length()-1; i>=halfway; i--) {
			second.append(str.charAt(i));
		}
		
		String firstString = first.toString();
		String secondString = second.toString();
		return firstString.equalsIgnoreCase(secondString);
	}

}
