package prac1;
import java.util.*;

public class as1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
	
	 String s;
	
	
	System.out.println("Please input your string");
	s = input.next();
	
	char[] value = new char [s.length()]; 
     
     for (int i = 0; i < s.length(); i++){
 
     
	value[i] = s.charAt(i);// Separate the string into individual value and puts them into their own array
     }
     
     char[] value2 = new char [s.length()]; 
     
     for (int j = 0; j < s.length(); j++){
    	 
         
    		value2[j] = value[s.length() - 1 - j];// Puts String value into new array that is reverse of old array
    	     }
     
     String s2 = new String(value2);
     System.out.println(s2);
     
}
}





