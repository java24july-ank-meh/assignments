package com.homework.Q19;

import java.util.ArrayList;
import java.util.List;

public class Q19 {

	public static void main(String[] args) {
		
		int sum = 0;
		List<Integer> list = new ArrayList<>();
		List<Integer> non_prime = new ArrayList<>();
		
		for(int i = 1; i <11; ++i)
			list.add(i);
		for(int i = 1; i < 11; i+=2)
			sum += list.get(i);
		System.out.println(sum);
		sum = 0;
		for(int i = 0; i < 10; i+=2)
			sum += list.get(i);
		System.out.println(sum);
		
		for(int i = 1; i < list.size(); ++i) {
			for(int j = 1; j < i; ++j) {
				if((list.get(i)%list.get(j)) == 0) {
					non_prime.add(list.get(i));
							break;
				}	
			}
		}
		for(int i : non_prime)
			System.out.print(i + " ");
	}
}
