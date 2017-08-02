package even;

public class Even {

	
	// Test even without using modulus operator
	public static boolean isEven(int num) {
		if ((num % 2) == 0) {
			return true;
		} else {
			return false; 
		}
	}
	
	
	public static void main(String[] args) {

		System.out.println(isEven(4));
		
	}

}
