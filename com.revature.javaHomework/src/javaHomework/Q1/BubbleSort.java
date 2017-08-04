package javaHomework.Q1;

import java.util.Arrays;


public class BubbleSort {

	static void bubbleSort(int[] arr) {

		int l = arr.length;
		int temp;

		// compare each pair of array elements in double loop
		for (int i = 0; i < l; i++) {
			for (int j = 1; j < (l - i); j++) {

				// swap elements if out of order
				if (arr[j - 1] > arr[j]) {

					temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}

			}
		}

	}
	

	public static void main(String[] args) {

		int[] arr = { 1, 0, 5, 6, 3, 2, 7, 9, 8, 4 };
		bubbleSort(arr);
		
		System.out.println("Sorted array: " + Arrays.toString(arr));
	}

}
