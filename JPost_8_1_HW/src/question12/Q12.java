package question12;

public class Q12 {
	/*
	 * Question 12: Write a program to store numbers from 1 to 100
	 * in an array. Print out all the even numbers from the array.
	 * Use the enhanced FOR loop for printing out the numbers.
	 */
	public static void main(String[] args) {
		System.out.println("Test");
		int[] arr = new int[100];
		for(int x = 0; x < 100; x++) {
			arr[x] = x+1;
		}
		for(int x : arr) {
			if(x%2 == 0)
				System.out.print(x + " ");
		}
	}
}
