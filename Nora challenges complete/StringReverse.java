
public class StringReverse {

	public static String revNoTemp(String revMe) {
		int length = revMe.length();
		for(int i = 0; i < length; i++) {
			
			//char temp = revMe.charAt(length - 1);
			
			//revMe.substring(0, length - 1); //take away the last character
			//revMe.substring(0, i + 1); //the part of the string that has already been reversed
			//revMe.substring(i + 1, length - 1); //the rest of the string minus the part of the string we are moving
			revMe = revMe.substring(0, i) + revMe.charAt(length - 1) + revMe.substring(i, length - 1);
		}
		return revMe;
	}
	
	public static String subMe(String str, int idx) {
		char[] stuff = str.toCharArray();
		String temp = "";
		
		for(int i = 0; i < idx - 1; i++) {
			temp += stuff[i];
		}
		return temp;
	}
	
	public static void main(String[] args) {
		String test1 = "My first test";
		
		System.out.println(revNoTemp(test1));

		System.out.println(subMe(test1, 9));

	}
	/* 1) Reverse a string without using a temp variable and without using reverse.
	 * 2) Write a substring method that accepts a string str and integer idx
	 * and returns the substring between the 0 and idx-1 inclusive. No using
	 * any existing substring methods.*/
}
