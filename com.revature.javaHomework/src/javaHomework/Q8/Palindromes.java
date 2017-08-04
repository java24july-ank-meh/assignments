package javaHomework.Q8;

import javaHomework.Q6.IsEven;
import java.util.ArrayList;

public class Palindromes {

	// method that finds palindromes in arr1 and returns arr2 containing those
	// palindromes
	static ArrayList<String> palFinder(ArrayList<String> arr) {
		boolean isPal;
		ArrayList<String> pal = new ArrayList<String>();

		// iterate through each String in arr
		for (String word : arr) {
			isPal = true;
			int l = word.length();
			// check that word is correct length to be a palindrome
			if (l > 1 && IsEven.isEven(l)) {
				continue;
			}

			// iterate forwards and backwards through word, check that chars at corresponding indices are equal
			for (int i = 0, j = (l - 1); i < (l / 2); i++, j--) {
				if( !( word.charAt(i)==(word.charAt(j)) ) ) {
					isPal = false;
					break;
				}
			}
			
			//if word is palindrome add to ArrayList 
			if(isPal) {
				pal.add(word);
			}
		}

		return pal;
	}

	public static void main(String[] args) {
		// create and populate ArrayList
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("karan");
		arr.add("madam");
		arr.add("tom");
		arr.add("civic");
		arr.add("radar");
		arr.add("sexes");
		arr.add("jimmy");
		arr.add("kayak");
		arr.add("john");
		arr.add("refer");
		arr.add("billy");
		arr.add("did");
		
		//pull palindromes from arr
		ArrayList<String> pals = palFinder(arr);
		
		//print pals
		for(String pal: pals) {
			System.out.println(pal+" is a palindrome");
		}

	}

}
