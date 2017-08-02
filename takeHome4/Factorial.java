package revature.takeHome4;

public class Factorial {

	public static void main(String[] args) {
		int bigNum = factorial(10);
		System.out.println(bigNum);
	}

	public static int factorial(int x) {
		int retInt = 0;
		if (x == 0) {
			return 0;
		} else if (x == 1) {
			return 1;
		} else {
			retInt = factorial(x - 1) * x;
		}

		return retInt;

	}
}
