package as2;

import java.util.Scanner;

public class Factorial {
	 public static void main(String[] args) {
		 Scanner input = new Scanner(System.in);
			System.out.println("Enter your number");
			int x = input.nextInt();
			System.out.println("Factoring " + x);
			int result = Factor(x);
			System.out.println("Your result is" + result);


	 }

	 public static int Factor(int n) {
			int result = 1;
		for (int i = 1; i <= n; i++) {
			   result = result * i;
			}
		return result;
		}
}

