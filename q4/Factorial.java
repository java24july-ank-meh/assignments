package q4;

import java.util.Scanner;

public class Factorial {
	 public static void main(String[] args) {
		 Scanner input = new Scanner(System.in);
			System.out.println("Enter your number");
			int x = input.nextInt();// input user defined number
			System.out.println("Factoring " + x);
			int result = Factor(x);//factorial method
			System.out.println("Your result is" + result);//print factorial


	 }

	 public static int Factor(int n) {
			int result = 1;// factorial multiplies
		for (int i = 1; i <= n; i++) {//calculate factorial of input number
			   result = result * i;
			}
		return result;
		}
}
