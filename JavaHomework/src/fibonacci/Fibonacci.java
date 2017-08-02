package fibonacci;

public class Fibonacci {

	
	// Prints out 0 to upto fibonacci numbers
	public static void displayFibonacci(int upto) {
	
		if (upto > 0) {
			int fib1 = 0;
			int fib2 = 1;
			
			for (int i = 0; i < upto; i++) {
				System.out.println(fib1);
				int temp = fib1;
				fib1 = fib2;
				fib2 = temp + fib2;
			}	
		}
	}
	
	public static void main(String[] args) {
		
		int upto = 25;
		displayFibonacci(1);
		
		
	}
	
}
