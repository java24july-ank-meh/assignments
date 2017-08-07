package tester;

import java.util.HashMap;
import java.util.HashSet;

public class Test{
	public static void main(String[] args) {
		System.out.println(isSubstitutionCipher("abc","def"));
		
	}
	
	static void print(String s) {
		System.out.println(s);
	}
	static void print(int s) {
		System.out.println(s);
	}
	static boolean isSubstitutionCipher(String string1, String string2) {
	    int length = string1.length();
	    
	    HashMap<Character, Character> replacementMap = new HashMap<>();
	    
	    for (int i = 0; i < length; i++) {
	        Character char1 = string1.charAt(i);
	        Character char2 = string2.charAt(i);
	        
	        if (replacementMap.containsKey(char1)) {
	            Character previousChar2 = replacementMap.???(char1);
	            if (!char2.equals(?????????????)) {
	                return false;
	            }
	        } else {
	            replacementMap.???(char1, char2);
	        }
	    }
	    
	    return replacementMap.values() == new HashSet<>(replacementMap.values()).();
	}


}
