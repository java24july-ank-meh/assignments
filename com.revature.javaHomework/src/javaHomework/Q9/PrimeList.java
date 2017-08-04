package javaHomework.Q9;

import java.util.ArrayList;

public class PrimeList {

	//trivial function to check if int n is prime
	public static boolean isPrime(int n) {
		//check that n is not divisible by any number from 2 - n/2
		//I just do this to make it a little faster (if x doesn't divide n then 2x won't either)
		for (int i = 2; 2 * i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {

		// create ArrayList containing values 1-100
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 1; i < 101; i++) {
			arr.add(i);
		}

		// print out primes in arr
		for (int i = 0; i < arr.size(); i++) {

			if (isPrime( arr.get(i) )) {
				System.out.println( arr.get(i) + " is prime");
			}
		}

	}
}
