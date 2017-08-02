package revature.takeHome2;

public class RecursivePractice {

	public static void main(String[] args) {
		// int test = fibonacci(25);
		// System.out.println(test);
		for (int i = 0; i <= 25; i++) {
			System.out.println(fibonacci(i));

		}

		System.out.println();

	}

	public static int fibonacci(int x) {
		int retInt = 0;
		if (x == 0) {
			return 0;
		} else if (x == 1) {
			return 1;
			// base cases, bc 1 will always be 1
		} else {
			retInt = fibonacci(x - 1) + fibonacci(x - 2);
			// here is the logic, with the fibonacci(x-1) meaning you have to do
			// fibonacci((x-1) +(x-2)) +1) and so on
		}
		return retInt;
	}

}
