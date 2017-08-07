package question02;

public class Q2 {
	/*
	 * Question 2: Write a program to display the first 25
	 * Fibonacci numbers beginning at 0
	 */
	public static void main(String[] args) {
		//Q2 Test
		System.out.println("Test");
		fibonacci();
	}
	static void fibonacci () {
		fibonacci(0,1,0);
	}
	static void fibonacci(int first, int second, int count) {
		if(count==25)
			return;
		System.out.println(first);
		fibonacci(second,first+second,count+1);
	}
}
