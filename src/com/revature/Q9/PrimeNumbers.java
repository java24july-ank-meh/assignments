package com.revature.Q9;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers {
	public static void main (String[] args) {
        System.out.println ("prime numbers between 1 and 100 are: ");
        List<Integer> primeNumbers = new ArrayList<Integer>();
        boolean checkPrime;
        for (int index = 2; index < 100; index++) {
            checkPrime = true; 
            for (int i = 2; i < index; i++) {
                if (index%i == 0) 
                		checkPrime = false; 
            }
            if (checkPrime) { 
                primeNumbers.add(index); 
            }
        }
        System.out.println(primeNumbers);
    }
}
