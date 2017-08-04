package com.revature.Q1;

public class Q1 {
	
	public static int[] bubbleSort(int[] a) {
		boolean swap = true;
		int temp;

		while (swap == true) {
			swap = false;
			for (int i = 1; i < a.length; i++) {
				if (a[i - 1] > a[i]) {
					temp = a[i];
					a[i] = a[i - 1];
					a[i - 1] = temp;
					swap = true;
					System.out.println();
				}
			}
		}
		return a;
	}

	public static void main(String[] args) {
		int[] array1 = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		array1 = bubbleSort(array1);
		for (int i = 0; i < array1.length; i++) {
			System.out.println(array1[i]);
		}

	}

}
