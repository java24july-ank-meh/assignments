package Q1;

public class Question1 {
	
	/*
	 * Q1 Perform a bubble sort on the following integer array: 1,0,5,6,3,2,3,7,9,8,4
	 */
	public static int[] myBubbleSort(int arr[]) {
		for(int i=arr.length-1;i>=0;i--) {
			for(int j=0;j<i;j++) {
				if(arr[j]>arr[j+1]) {
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		
		return arr;
	}
	
	public static void main(String args[]) {
		int a[]= {1,0,5,6,3,2,3,7,9,8,4};
		myBubbleSort(a);
	}
}
