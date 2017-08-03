package com.revature.codechallenge;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayList10 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<>();
		
		for (int i = 1; i <= 10 ; ++i) {
			al.add(i);
		}
		
		System.out.println(evenSum(al));
		
		System.out.println(oddSum(al));
		
		System.out.println((removePrimes(al).toString()));
	}
	
	public static int evenSum(ArrayList<Integer> al) {
		int sum = 0;
		for (Integer i : al) {
			if (i % 2 == 0)
				sum += i;
		}
		
		return sum;
	}
	
	public static int oddSum(ArrayList<Integer> al) {
		int sum = 0;
		for (Integer i : al) {
			if (i % 2 != 0)
				sum += i;
		}
		
		return sum;
	}
	
	public static ArrayList<Integer> removePrimes(ArrayList<Integer> al) {
		Iterator<Integer> i = al.iterator();
		while (i.hasNext()) {		
			if (isPrime(i.next()))
				i.remove();
		}
		
		return al;
	}
	
	public static boolean isPrime(int x) {
			int check =(int) Math.sqrt(x);
			boolean pass = true;
			
			for (int z = 2; z <= check; ++z) {
				if (x % z == 0) {
					pass = false;
					break;
				}
			}
			
			return pass;
	}
}
