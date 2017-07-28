package as2;

import java.util.Scanner;

public class Fibonacci {
	 public static void main(String[] args) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter your number");
			int x = input.nextInt();
			fibonacciLoop(x);
			
		 
	 }
	 public static void fibonacciLoop(int number) {
		 System.out.print(0 + " " + 1 + " ");
		 
	 if (number == 1 || number == 2) {
			System.out.print("1");
		}
		int fibo1 = 1, fibo2 = 1, fibonacci = 1;
		for (int i = 3; i <= number; i++) {
			fibonacci = fibo1 + fibo2; // 
			fibo1 = fibo2;
			fibo2 = fibonacci;
			System.out.print(fibonacci + " ");

		}
		
	}
}
