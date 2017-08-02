package q3;
import java.util.Scanner;

public class strRev {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
	
	 String s;
	
	
	System.out.println("Please input your string");
	s = input.next();//input string S
	
	char[] value = new char [s.length()]; 
     
     for (int i = 0; i < s.length(); i++){
 
     
	value[i] = s.charAt(i);// Separate the string into individual values and puts them into their own cracter array
     }
     
     char[] value2 = new char [s.length()]; 
     
     for (int j = 0; j < s.length(); j++){
    	 
         
    		value2[j] = value[s.length() - 1 - j];// Puts String value into new array that is reverse of old array
    	     }
     
     String s2 = new String(value2);// puts character array into string values
     System.out.println(s2);
     
}
}





