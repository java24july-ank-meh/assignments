package javaHomework.Q2;

public class Fibonacci {
	public static int fib(int N) {
		if (N == 0) {
			return 0;
		} else if (N == 1) {
			return 1;
		} else {
			return fib(N - 1) + fib(N - 2);
		}
	}
	
	public static void main(String[] args) {

		System.out.println("the 25th number in the fibonacci sequence is: "+fib(25));

	}
}
