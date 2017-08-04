package javaHomework.Q4;

public class Factorial {
	public static int fact(int N) {
		if (N == 0) {
			return 0;
		} else if (N == 1) {
			return 1;
		} else {
			return fact(N - 1) * N;
		}
	}

	public static void main(String[] args) {

		System.out.println("3! = " + fact(3));
		System.out.println("5! = " + fact(5));

	}
}
