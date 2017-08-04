package Q8;

import java.util.ArrayList;

import Q3.Question3;

public class Question8 {
	
	/*
	 * Q8 Write a program that stores the following strings in an ArrayList and saves
	 * all the palindromes another ArrayList.
	 * 
	 * This is a helper method to detect if a string is a palindrome
	 */
	public static boolean isPalindrome(String str) {
		char newStr[]=str.toCharArray();
		int end = newStr.length-1;
		for(int i=0;i<(newStr.length/2);i++) {
			if(newStr[i]==newStr[end]) {
				end--;
				continue;
			}
			else
				return false;
		}
		return true;
	}
	
	/*
	 * Q8 Write a program that stores the following strings in an ArrayList and saves
	 * all the palindromes another ArrayList.
	 * 
	 * This is a helper method to make a non-palindrome word a palindrome
	 */
	public static String makePalindrome(String str){
		return str+=Question3.reverseString(str);
	}
	
	public static ArrayList<String> createPalindromeArrayList(ArrayList<String> list){
		ArrayList<String> newList = new ArrayList<String>();
		for(int i=0;i<list.size();i++) {
			if(isPalindrome(list.get(i))) 
				newList.add(list.get(i));
			else 
				newList.add(makePalindrome(list.get(i)));
		}
		return newList;
	}

	public static void main(String[] args) {
		ArrayList<String> words =  new ArrayList<String>();
		words.add("karan");
		words.add("madam");
		words.add("tom");
		words.add("civic");
		words.add("radar");
		words.add("sexes");
		words.add("jimmy");
		words.add("kayak");
		words.add("john");
		words.add("refer");
		words.add("billy");
		words.add("did");
		System.out.println();
		System.out.println(createPalindromeArrayList(words).toString());
	}

}
