package com.Revature.primes;

import java.util.ArrayList;

public class Printprime {
	static boolean isPrime(int n) {
	    for(int i=2;i<n;i++) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	public static void printout(ArrayList<Integer> numbers){
		System.out.println("Primes: ");
		for(int i : numbers){
			if (isPrime(i)){
				System.out.print(i + " ");
			}
		}
	}
}
