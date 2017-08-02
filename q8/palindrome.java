package q8;

import java.util.*;

public class palindrome {
	public static void main(String args[]){  
		ArrayList<String> Palindrome = new ArrayList<String>();//create palindrome array
		ArrayList<String> arrayList = new ArrayList<String>();//create normal array
		arrayList.add("karan");
		arrayList.add("madam");
		arrayList.add("tom");
		arrayList.add("civic");
		arrayList.add("radar");
		arrayList.add("sexes");
		arrayList.add("jimmy");
		arrayList.add("kayak");
		arrayList.add("john");
		arrayList.add("refer");
		arrayList.add("billy");
		arrayList.add("did");
		
		String[] checker = new String[arrayList.size()];//create new list the same size as ArrayList
		checker = arrayList.toArray(checker);// ccreate string array with values from array list
		for(String s : checker)
		    if(isPali(s)==true) {
		    	Palindrome.add(s);
		    }// repeat string conversion to check if second array has all the palindromes
		String[] tester = new String[Palindrome.size()];
		tester = Palindrome.toArray(tester);
		
		for(String s : tester)
			System.out.println(s);

	}
	
	public static boolean isPali(String s) {
		char[] value = new char [s.length()]; 
	     
	     for (int i = 0; i < s.length(); i++){
	 
	     
		value[i] = s.charAt(i);// Separate the string into individual values and puts them into their own character array
	     }
	     
	     int i1 = 0;
	     int i2 = value.length - 1;//check if individual characters on both ends of the string match
	     while (i2 > i1) {
	         if (value[i1] != value[i2]) {
	             return false;
	         }
	         ++i1;
	         --i2;
	     }
	     return true;
		
	}
}
