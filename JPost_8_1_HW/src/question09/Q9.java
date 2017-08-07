package question09;

import java.util.ArrayList;

public class Q9 {
	/*
	 * Question 9: Create an ArrayList which stores numbers from
	 * 1 to 100 and prints out all the prime numbers to the console
	 */
	public static void main (String[] args) {
		System.out.print("Test\nPrimes -> ");
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x = 1; x <= 100; x++) {
			arr.add(x);
		}
		for(Integer i : arr) {
			if(isPrime(i))
				System.out.print(i+" ");
		}
		System.out.println();
	}
	public static boolean isPrime(int i) {
		for(int x = 2; x < i; x++) {
			if(i%x == 0)
				return false;
		}
		return true;
	}
}
