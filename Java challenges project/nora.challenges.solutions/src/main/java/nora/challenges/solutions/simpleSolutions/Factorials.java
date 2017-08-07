package nora.challenges.solutions.simpleSolutions;

public class Factorials {

	public static void main(String[] args) {
		System.out.println("7! = " + getFactorial(7));
		System.out.println("13! = " + getFactorial(13));
		System.out.println("4! = " + getFactorial(4));

	}
	
	private static int getFactorial(int num) {
		if(num == 0 || num == 1) return num;
		
		int total = 1;
		
		for(int i = 1; i < num + 1; i++) {
			total *= i;
		}
		return total;
	}

}
