package question06;

public class Q6 {
	/*
	 * Question 6: Write a program to determine if an 
	 * integer is even without using the modulus operator(%)
	 */
	public static void main(String[] args) {
		//Q6 Test
		System.out.println("Test\n6 is even: " + isEven(6)+"\n9 is even: " + isEven(7));
	}
	
	public static boolean isEven(int n) {
		if((n/2) == ((n+1)/2))
			return true;
		return false;
	}
}
