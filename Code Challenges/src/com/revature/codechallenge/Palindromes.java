package com.revature.codechallenge;

import java.util.ArrayList;
import java.util.Arrays;

public class Palindromes {
	public static void main(String[] args) {
		ArrayList<String> strs = new ArrayList<>();
		ArrayList<String> palindromes = new ArrayList<>();
		strs.addAll(Arrays.asList("karan", "madam", "tom", "civic", "radar", "sexes", "jimmy", "kayak", "jon", "refer", "billy", "did"));
		System.out.println(strs);
		
		for(String s : strs) {
			if (new StringBuilder(s).reverse().toString().equals(s)) {
				palindromes.add(s);
			}
		}
		
		System.out.println(palindromes);
	}
}
