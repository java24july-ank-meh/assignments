package com.homework.Q8;

import java.util.ArrayList;

public class Q8 {

	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<>();
		ArrayList<String> palindromes = new ArrayList<>();

		strings.add("karan");
		strings.add("madam");
		strings.add("tom");
		strings.add("civic");
		strings.add("radar");
		strings.add("sexes");
		strings.add("jimmy");
		strings.add("kayak");
		strings.add("john");
		strings.add("refer");
		strings.add("billy");
		strings.add("did");

		//iterate through strings
		for (String s : strings) {
			//check if characters closing in are equal
			for (int i = 0; i < s.length() / 2; ++i) {
				if (s.charAt(i) == s.charAt(s.length() - 1 - i))
					if (i == s.length() / 2 - 1) {
						palindromes.add(s);
					}
			}
		}
		System.out.println(palindromes.toString());
	}

}
