package com.revature.q1;

import java.util.Arrays;

public class Q1 {
	
	public static void main(String[] args) {
		int[] a = {1,0,5,6,3,2,3,7,9,8,4};
		bubbleSort(a);
		System.out.println(Arrays.toString(a));
	}

	public static void bubbleSort(int[] a) {
		int temp;
		//outer loop determines array element to be "bubbled" to correct index
		for(int i=1; i<a.length; i++) {
			/*inner loop iterates down from original index until a[j-1] is less than or equal
			 * to a[j]. 
			 * Invariant: at the beginning of each iteration of the inner loop, the first j-1
			 * elements are sorted with respect to themselves. At the end of each iteration, 
			 * the first j elements are sorted.
			 */
			for(int j=i; j>0; j--) {
				if(a[j] < a[j-1]) {
					temp = a[j-1];
					a[j-1] = a[j];
					a[j] = temp;
				}
			}
		}
	}
}
