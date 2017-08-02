package revature.takeHome8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Palindrome {

	public static void main(String[] args) {
		ArrayList<String> paliCheck = new ArrayList<String>();
		Collections.addAll(paliCheck, "karan", "madam", "tom", "civic", "radar", "sexes", "jimmy", "kayak", "john",
				"refer", "billy", "did");

		ArrayList<String> defPalin = new ArrayList<String>();
		for (String ran : paliCheck) {
			if (isPalindrome(ran)) {
				defPalin.add(ran);
			}
		}

		paliCheck.removeAll(defPalin);

		System.out.println(Arrays.toString(paliCheck.toArray()));
		System.out.println(Arrays.toString(defPalin.toArray()));

	}

	public static boolean isPalindrome(String word) {
		int begin = 0;
		int end = word.length();
		while (end > begin) {
			if (word.charAt(begin) != word.charAt(end - 1)) {
				return false;
			}

			begin++;
			end--;
		}
		return true;
	}
}
