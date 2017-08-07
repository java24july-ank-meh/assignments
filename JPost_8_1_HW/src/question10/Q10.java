package question10;

public class Q10 {
	/*
	 * Question 10: Find the minimum of two numbers using
	 * ternary operators.
	 */
	public static void main(String[] args) {
		System.out.println("Test");
		System.out.println("4 vs 5: " + min(4,5));
		System.out.println("6 vs 3: " + min(6,3));
	}
	
	public static int min(int a, int b) {
		int minVal = (a < b) ? a : b;
		return minVal;
	}
}
