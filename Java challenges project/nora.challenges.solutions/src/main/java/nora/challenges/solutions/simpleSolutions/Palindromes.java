package nora.challenges.solutions.simpleSolutions;

import java.util.ArrayList;

public class Palindromes {

	public static void main(String[] args) {
		String[] stuff = {"karin", "madam", "tom", "civic", "radar", "sexes", "jimmy", "kayak", "john",
				"refer", "billy", "did"};
		ArrayList<String> palindromes = new ArrayList<String>();
		
		for(String temp : stuff) {
			if(isPalindrome(temp)) {
				palindromes.add(temp);
			}
		}
		
		System.out.println("Palindromes:");
		for(String temp : palindromes) {
			System.out.print(temp + " ");
		}

	}

	private static boolean isPalindrome(String temp) {
		for(int i = 0; i < temp.length() /2; i++) {
			if(temp.charAt(i) != temp.charAt(temp.length() - 1 - i)) return false;
		}
		
		
		return true;
	}

}
