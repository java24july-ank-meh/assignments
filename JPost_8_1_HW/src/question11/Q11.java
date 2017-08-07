package question11;
import question11pt2.Q11pt2;;
public class Q11 {
	/*
	 * Question 11: Write a program that would access two float-variables
	 * from a class that exists in another package. Note, you will need 
	 * to create two packages to demonstrate the solution
	 */
	public static void main(String[] args) {
		Q11pt2 example = new Q11pt2((float)1.234, (float)5.678);
		System.out.println("Test");
		System.out.println("Float 1: " + example.getA());
		System.out.println("Float 2: " + example.getB());
	}
}
