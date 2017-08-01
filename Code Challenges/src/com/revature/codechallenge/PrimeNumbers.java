package com.revature.codechallenge;

import java.util.ArrayList;

public class PrimeNumbers {
	public static void main(String[] args) {
		ArrayList<Integer> primes = new ArrayList<>();
		
		for (int i = 1; i <=100; ++i) {
			primes.add(i);
		}
		
		for (int i: primes) {
			int check =(int) Math.sqrt(i);
			boolean pass = true;
			
			for (int z = 2; z <= check; ++z) {
				if (i % z == 0) {
					pass = false;
					break;
				}
			}
			
			if (pass)
				System.out.println(i);
		}
	}
}
