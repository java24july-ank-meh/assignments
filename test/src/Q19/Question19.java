package Q19;

import java.util.ArrayList;

import Q9.Question9;

/*
 * Q19 Create an ArrayList and insert integers 1 through 10. Display the ArrayList. Add
 * all the even numbers up and display the result. Add all the odd numbers up and display
 * the result. Remove the prime numbers from the ArrayList and print out the remaining 
 * ArrayList.
 */
public class Question19 {

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i=1;i<=10;i++)
			arr.add(i);
		System.out.println(arr.toString());
		
		int evenSum=0;
		for(int i=0; i<arr.size();i++)
			if(arr.get(i).intValue()%2==0)
				evenSum+=arr.get(i).intValue();
		System.out.println(evenSum);
		
		int oddSum=0;
		for(int i=0; i<arr.size();i++)
			if(arr.get(i).intValue()%2==1)
				oddSum+=arr.get(i).intValue();
		System.out.println(oddSum);
		
		for(int i=0;i<arr.size();i++)
			if(Question9.isPrime(arr.get(i).intValue()))
				arr.remove(i);
		System.out.println(arr.toString());

	}

}
