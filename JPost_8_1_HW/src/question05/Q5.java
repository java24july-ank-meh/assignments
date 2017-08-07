package question05;

public class Q5 {
	/*
	 * Question 5: Write a substring method that accepts a string 
	 * str and an integer idx and returns the substring contained 
	 * between 0 and idx-1 inclusive. Do NOT use any of the existing 
	 * substring methods in the String, StringBuilder, or
	 * StringBuffer APIs.
	 */
	public static void main(String[] args) {
		//Q5 Test
		System.out.println("Test\nsubstr(hello,2) = " + substring("hello",2));
	}
	
	public static String substring(String string, int idx) {
		StringBuilder sb = new StringBuilder();
		for(int x = 0; x <= idx; x++) {
			sb.append(string.charAt(x));
		}
		return sb.toString();
	}
}
