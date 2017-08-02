package q9;

import java.util.ArrayList;

public class prime {
	
	public static void main(String args[]){  
		ArrayList<Integer> total = new ArrayList<Integer>();//create integer array
		System.out.println(" Now printing prime numbers and addin 1 - 100 into array list");
		System.out.println();
		for(int i = 1;i <101; i++) {//add values to array list
			total.add(i);
			if(isPrime(i)== true)
				System.out.print(i + " ");
		}
		

	}

	
	public static boolean isPrime(int n) {
		   if (n <= 1) {// 0 and 1 do not count as either prime or composite
		       return false;
		   }
		   for (int i = 2; i < Math.sqrt(n); i++) {
		       if (n % i == 0) {// if any number besides 1 evenly divides the input, it is composite
		           return false;
		       }
		   }
		   return true;
		}
}
