package com.revature.q9;

import java.util.ArrayList;

public class Q9 {
	
	public static void main(String[] args) {
		ArrayList<Integer> arr = seive(100);
		for(Integer i: arr) {System.out.print(i + " ");}
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
}
