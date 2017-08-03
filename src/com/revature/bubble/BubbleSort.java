package com.revature.bubble;

class BubbleSort {
	void bubbleSort(int arr[]) {
		int length = arr.length;
		int temp;
		for(int i=0; i < length-1; i++) {
			for(int j=0; j < length-i-1; j++) {
				if(arr[j] > arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	public static void main(String[] args) {
		BubbleSort obj = new BubbleSort();
		int[] arr = {1, 0, 5, 6, 3, 2, 7, 9, 8, 4};
		obj.bubbleSort(arr);
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}

}
