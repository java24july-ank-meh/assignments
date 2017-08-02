package print_primes;

import java.util.ArrayList;

public class PrintPrimes {

	public static boolean isPrime(Integer number, ArrayList<Integer> primes) {
		
		double squareRoot = Math.sqrt(number);
		
		for (Integer prime: primes) {
			if (prime <= squareRoot) {
				if (prime > 1 && (number % prime) == 0) {
					return false;
				}
			} else {
				break;
			}
		}
		return true;
	}
	
	public static void printPrimes(ArrayList<Integer> numbers) {
		
		ArrayList<Integer> primes = new ArrayList<>();
		primes.add(2);
		
		for (int i = 2; i < numbers.size(); i++) {
			Integer val = numbers.get(i);
			if (isPrime(val, primes)) {
				primes.add(val);
			}
		}	
		
		for (Integer prime: primes) {
			System.out.println(prime);
		}
		
	}

	public static void main(String[] args) {
		
		ArrayList<Integer> integers = new ArrayList<>();
		
		for (int i = 1; i <= 100; i++) {
			integers.add(i);
		}
		
		printPrimes(integers);
		
	}

}
