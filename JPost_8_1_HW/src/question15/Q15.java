package question15;

public class Q15 {
	/*
	 * Question 15: Write a program that defines an interface having the
	 * following methods: addition, subtraction, multiplication, 
	 * and division. Create a class that implements this interface and provides
	 * appropriate functionality to carry out the required operations.
	 * Hard code two operands in a test class having a main method
	 * that calls the implementing class.
	 */
	public static void main(String[] args) {
		Calculator c = new Calculator(12,4);
		System.out.println("12 + 4 = "+c.addition());
		System.out.println("12 - 4 = "+c.substration());
		System.out.println("12 * 4 = "+c.multiplication());
		System.out.println("12 / 4 = "+c.division());
	}
}
