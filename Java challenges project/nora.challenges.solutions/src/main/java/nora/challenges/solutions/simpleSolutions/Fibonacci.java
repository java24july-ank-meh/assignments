package nora.challenges.solutions.simpleSolutions;

public class Fibonacci {

	public static void main(String[] args) {
		printFibonacci25();

	}
	
	private static void printFibonacci25() {
		int temp1 = 0; //1 -> 2 = temp2
		int temp2 = 1; //2 -> 3 = temp1 + temp2
		for(int i = 0; i < 25 + 1; i++) {
			System.out.println("Fibonacci number " + i + ": " + temp1);
			int oldTemp = temp2;
			temp2 = temp1 + temp2;
			temp1 = oldTemp;
		}
		
	}

}
