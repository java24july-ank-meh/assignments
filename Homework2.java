public class Homework2 {

	// FIBONACCI
	public static int fib(int N) {
		if (N == 0) {
			return 0;
		} else if (N == 1) {
			return 1;
		} else {
			return fib(N - 1) + fib(N - 2);
		}
	}

	// FACTORIAL
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

		System.out.println("3! = "+fact(3));
		System.out.println("5! = "+fact(5));

		System.out.println("the 25th number in the fibonacci sequence is: "+fib(25));

	}
}
