package javaHomework.Q12;

//import IsEven class from previous problem
import javaHomework.Q6.IsEven;

public class PrintEven {

	public static void main(String[] args) {
		// create int array with 100 indices
		int[] arr = new int[100];

		// store nums 1 - 100 in an array
		for (int i = 0; i < 100; i++) {
			arr[i] = i + 1;
		}

		// iterate through arr in fancy for loop and print elements which are even
		for (int i : arr) {
			if ((IsEven.isEven(i)) == true) {
				System.out.print(Integer.toString(i) + ", ");
				// print formating logic
				if (i % 20 == 0) {
					System.out.println("");
				}
				
			}
		}
		

	}

}
