package odds_evens_primes;

import java.util.ArrayList;

public class SumOddsEvensFindPrimes {

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
	
	public static void sumEvensOddsPrintPrimes(ArrayList<Integer> list) {
		
		int odd_sum = 0;
		int even_sum = 0;
		ArrayList<Integer> primes = new ArrayList<>();
		primes.add(2);
	
		
		for (int i = 0; i < list.size(); i++) {
			Integer val = list.get(i);
			
			if (isPrime(val, primes)) {
				primes.add(val);
			} 

			if ((val % 2) == 0) {
				even_sum += val;
			} else {
				odd_sum += val;
			}
		}
		
		System.out.println("Odd Sum: " + odd_sum);
		System.out.println("Even Sum: " + even_sum);
		System.out.println("List without primes: " + primes);
		
		
	}
	
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int i = 0; i <= 10; i++) {
			list.add(i);
		}
		
	}

}
