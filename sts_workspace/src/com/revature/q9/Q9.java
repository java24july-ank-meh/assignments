package com.revature.q9;

import java.util.ArrayList;

public class Q9 {
	
	public static void main(String[] args) {
		ArrayList<Integer> arr = seive(100);
		for(Integer i: arr) {System.out.print(i + " ");}
		System.out.println();
		//that way was dumb though, and not exactly what the question wanted
		//here's a way that's not so stupid
		
		ArrayList<Integer> secondTry = new ArrayList<>();
		for(int i=1; i<101; i++) {secondTry.add(i);}
		for(Integer i: secondTry) {
			if(isPrime(i)) {System.out.print(i + " ");}
		}
	}

	public static ArrayList<Integer> seive(int n){
		ArrayList<Integer> result = new ArrayList<>();
		for(int i=2; i<=n; i++) {result.add(i);} //fill list with integers 1 to 100
		
		/*
		 * use seive to remove composites. starting with 2, remove all multiples of each 
		 * consecutive Integer still in the list. 
		 * 
		 * NOTE: can't use iterator or for-each bc iterator
		 *  will throw a ConcurrentModificationException
		 */
		Integer multiple; int base;
		for(int i=0; i<result.size(); i++) {
			base = result.get(i);
			multiple = base*2;
			while(multiple <= n) {
				result.remove(multiple);
				multiple += base;
			}
		}
		return result;
	}
	
	public static boolean isPrime(int n) {
		
		if(n<2) {return false;}
		if(n==2) {return true;}
		
		int sqrt = (int)Math.ceil(Math.sqrt(n));
		/*
		 * check if input is divisible by any number from 2 upto ceiling of the number's 
		 * square root. If we pass the square root, and our input has not been divisible by 
		 * any of the previous numbers, the number must be prime. (bc to be a multiple of a 
		 * number larger than its square root, our input would also have to be a multiple of a
		 * number smaller than its square root).
		 */
		for(int i=2; i<=sqrt; i++) {
			if(n%i==0) {return false;}
		}
		
		return true;
	}
}
