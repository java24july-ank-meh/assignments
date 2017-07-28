package com.Revature.mavendemo;

public class StringCalculator {
	
	public static void main(String args[]){
		
	}
	/* 
	 *Create a String calc with method add.
	 *Method can take 0-2 numbers. WIll return sum.
	 *Returns 0 for empty string.
	 */
	
	
	public static int add(String numbers){
		int sum = 0;
		numbers = numbers.replaceAll("\\s+", "");
		String[] numbersarray = numbers.split(",");
		if(numbersarray.length > 2){
			System.out.println("Bad Dog!");
			throw new RuntimeException();
		}else {
			for (String number : numbersarray){
				if(!number.equals("")){
					sum += Integer.parseInt(number);
				} else{
					continue;
				}
			}
		}
		return sum;
	}
}
