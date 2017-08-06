package com.revature.assignments;

import java.util.ArrayList;

public class Question19 {

	public static void main(String[] args) {
		ArrayList<Integer> nums = new ArrayList();
		for(int i=1; i < 11; i++) {
			nums.add(i);
		}
		TestArray(nums);
 	}
	public static void TestArray(ArrayList<Integer> nums) {
		int evens= 0, odds=0;
		ArrayList<Integer> primes = new ArrayList();
		for(int cur : nums) {
			if(cur%2 == 0) {
				evens = evens + cur;
				if(cur == 2)
					primes.add(cur);
			}else if(cur%2 != 0) {
				odds = odds + cur;
			}
			for(int j = 2; j < cur; j++) {
				if(cur%j == 0) {		
					break;
				}else if(j == cur-1) {
					primes.add(cur);
				}
			}
		}
		nums.removeAll(primes);
		System.out.println("Total for all even numbers is "+evens);
		System.out.println("Total for all odds numbers is "+odds);
		for(int i = 0; i < primes.size();i++)
			System.out.println("The primes are: "+ primes.get(i));
		for(int i = 0; i < nums.size();i++)
			System.out.println("The rest are: "+ nums.get(i));
		
	}
}
