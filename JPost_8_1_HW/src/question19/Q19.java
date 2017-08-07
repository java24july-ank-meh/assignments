package question19;

import java.util.ArrayList;
import java.util.Arrays;

public class Q19 {
	/*
	 * Question 19: Create an ArrayList and insert integers 1 through 10. 
	 * Display the ArrayList. Add all the even numbers up and display the
	 * result. Remove the prime numbers from the ArrayList and print out the
	 * remaining ArrayList.
	 */
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		for(int x = 1; x <= 10; x++) {
			arr.add(x);
		}
		System.out.println(Arrays.toString(arr.toArray()));
		int even = 0, odd = 0;
		for(Integer x : arr) {
			if(x%2 == 0)
				even++;
			else
				odd++;
		}
		System.out.println("Evens: " + even);
		System.out.println("Odds: " + odd);
		for(int x = arr.size()-1; x >= 0; x--) {
			if(isPrime(arr.get(x)))
				arr.remove(x);
		}
		System.out.println(Arrays.toString(arr.toArray()));
	}
	
	public static boolean isPrime(int i) {
		for(int x = 2; x < i; x++) {
			if(i%x == 0)
				return false;
		}
		return true;
	}
}
