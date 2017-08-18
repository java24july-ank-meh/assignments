package com.revature.Q8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Palindromes {

	public static void main(String[] args) {
		String[] a = new String[] {"karan", "madam", "tom", "civic", 
				                     "radar", "sexes", "jimmy", "kayak", 
				                     "john", "refer", "billy", "did"};
		List<String> strings = Arrays.asList(a);
		System.out.println("all strings in the ArrayList: " + strings);
		
		List<String> palindromes = new ArrayList<>();
		System.out.print("list of palindromes in the ArrayList: ");
		for(String s : strings) {
			if (new StringBuilder(s).reverse().toString().equals(s)) {
				palindromes.add(s);
			}
		}
		System.out.println(palindromes);
	}
}
