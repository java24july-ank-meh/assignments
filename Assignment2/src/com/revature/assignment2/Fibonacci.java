package com.revature.assignment2;

import java.util.ArrayList;

public class Fibonacci
{
	/**
	 * Produces Fibbonaci sequence up until given int number.
	 * 
	 * @param number of fibbonanci numbers to calculate
	 * @return fibbonaci sequence as an ArrayList<Integer>
	 */
	public static ArrayList<Integer> fibbonaci(int number)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		list.add(0);
		list.add(1);
		int result = 1;
		
		while(list.size() <= number)
		{
			list.add(result);
			result += list.get(list.size() - 2 );
		}
		
		return list;
	}
	
	public static void main(String[] args)
	{
		System.out.println( fibbonaci(20) );
		
		System.out.println( fibbonaci(2) );
		
	}

	
}
