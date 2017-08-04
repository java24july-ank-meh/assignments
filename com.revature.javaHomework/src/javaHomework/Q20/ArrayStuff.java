package javaHomework.Q20;

import java.util.ArrayList;

import javaHomework.Q9.PrimeList;

public class ArrayStuff {

	public static void main(String[] args) {
		//create ArrayList with vaues 1-10
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 1; i < 11; i++) {
			arr.add(i);
		}
		
		//display array
		System.out.print("arr \n[ ");
		for (int i = 0; i < arr.size(); i++) {
			System.out.print( arr.get(i) + " " );
		}
		System.out.println(" ]");
		
		//add even nums
		int even_sum = 0;
		for (int i: arr) {
			if((i & 1) == 0) {
				even_sum += i;
			}
		}
		System.out.println("The sum of even numbers in arr is: " + even_sum);

		//add odd nums
		int odd_sum = 0;
		for (int i: arr) {
			if((i & 1) == 1) {
				odd_sum += i;
			}
		}
		System.out.println("The sum of odd numbers in arr is: " + odd_sum);
		
		//remove prime numbers from arr
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i: arr) {
			if(PrimeList.isPrime(i) == true) {
				primes.add(i);
			}
		}
		arr.removeAll(primes);
		
		//display new arr
		System.out.print("arr with prime nums removed: \n" + "[ ");
		for (int i = 0; i < arr.size(); i++) {
			System.out.print( arr.get(i) + " " );
		}
		System.out.println(" ]");
		
	}

}
