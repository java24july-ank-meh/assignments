
public class Challenge2 {

	public static void main(String[] args) {
		// 1) write a program to display the first 25 fibonacci numbers begining at 0.
		// 2) Write a program that calculates N factorial (so 3! is 3*2*1)
		
		printFibonacci25();
		System.out.println(getFactorial(7));
		System.out.println(getFactorial(2));
		
	}

	private static int getFactorial(int num) {
		if(num == 0 || num == 1) return num;
		
		int total = 1;
		
		for(int i = 1; i < num + 1; i++) {
			total *= i;
		}
		return total;
	}

	private static void printFibonacci25() {
		int temp1 = 0; //1 -> 2 = temp2
		int temp2 = 1; //2 -> 3 = temp1 + temp2
		for(int i = 0; i < 25 + 1; i++) {
			System.out.println("Fibonacci number: " + i + ": " + temp1);
			int oldTemp = temp2;
			temp2 = temp1 + temp2;
			temp1 = oldTemp;
		}
		
	}
	
	

}
