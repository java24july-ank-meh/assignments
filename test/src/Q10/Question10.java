package Q10;

public class Question10 {
	
	/*
	 * Q10 Find the minimum of two numbers using ternary operators.
	 */
	public static int minVal(int x, int y) {
		return (x>y)?x:y;
	}
	
	public static void main(String args[]) {
		System.out.println(minVal(2,2));
	}

}
