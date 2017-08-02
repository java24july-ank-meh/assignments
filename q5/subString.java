package q5;

import java.util.*;

public class subString {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		String str;
		int idx;
		
		
		System.out.println("Please input your string");
		str = input.next();
		
		System.out.println("Please input the number of characters into your substring");
		idx = input.nextInt();
		
		String k = subMeth(str,idx);
		System.out.println(k);
	}
	
	public static String subMeth(String sub, int x) {
		char[] value = new char [sub.length()]; 
	     
	     for (int i = 0; i < sub.length(); i++){
	 
	     
		value[i] = sub.charAt(i);// Separate the string into individual value and puts them into their own array
	     }
	     char[] fin = new char [x];
	     
	     for (int i = 0; i < x; i++){
	    	 
		     
	 		fin[i] = value[i];
	 	     }
	     String s2 = new String(fin);
	     return s2;
	     
	     
	}
}
