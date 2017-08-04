package Q9;

import java.util.ArrayList;

public class Question9 {
	
	/*
	 * Q9 Create an ArrayList which stores numbers from 1 to 100 and prints out
	 * all the prime numbers
	 * 
	 * Helper method to determine if a number is prime
	 */
	public static boolean isPrime(int n) {
		if(n<=2&&n>=0)
			return false;
		for(int i=2;i<n;i++) {
			if(n%i==0)
				return false;
		}
		return true;
	}
	
	/*
	 * Q9 Create an ArrayList which stores numbers from 1 to 100 and prints out
	 * all the prime numbers
	 */
	public static ArrayList<Integer> printPrimes1_100() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i=1;i<=100;i++)
			if(isPrime(i)) {
				arr.add(i);
				System.out.println(i);
			}
		return arr;
	}

	public static void main(String[] args) {
		ArrayList<Integer> primes = printPrimes1_100();

	}

}
