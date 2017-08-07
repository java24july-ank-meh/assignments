package question16;

public class Q16 {
	/*
	 * Question 16: Write a program to display the number of characters for
	 * a String input. The string should be entered as a command line 
	 * argument using (String[] args)
	 */
	public static void main(String[] args) {
		int sum = 0;
		for(String s : args) {
			sum+=s.length();
		}
		System.out.println("Length of input is " + sum);
	}
}
