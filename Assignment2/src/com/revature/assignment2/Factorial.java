package com.revature.assignment2;

public class Factorial
{
	
	/**
	 * Produces Factorial output of a given number using recursion.
	 * 
	 * factorial( 4 ) = 4! = 4 * 3 * 2 * 1;
	 * 
	 * @param number to get factorial from
	 * @return int number of factorial solution.
	 */
	public static int factorial(int number) 
	{
		int n = number;
	
		// base case n == 1
		if( n == 1 )
			return 1;
		else
		// n * n -1 
		return number * factorial(number - 1);
	}
	
	
	public static void main(String[] args)
	{
		System.out.println( factorial(4) );
		
		System.out.println( factorial(5) );
	}
}
