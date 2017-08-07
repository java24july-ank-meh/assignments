package question01;

public class Q1 {
	/*
	 * Question 1: Perform a bubble sort on the following integer
	 * array: 1,0,5,6,3,2,3,7,9,8,4
	 */
	public static void main(String[] args) {
		// Q1 Test
		System.out.print("Test\nBefore BubbleSort: ");
		int[] q1test = {1,0,5,6,3,2,3,7,9,8,4};
		for(int x = 0; x < q1test.length; x++) {
			System.out.print(q1test[x]+" ");
		}
		System.out.print("\nAfter BubbleSort:  ");
		q1test = bubbleSort(q1test);
		for(int x = 0; x < q1test.length; x++) {
			System.out.print(q1test[x]+" ");
		}
	}
	
	static int[] bubbleSort (int[] arr) {
		boolean flag = true;
		while(flag) {
			flag = false;
			for(int x = 0; x < arr.length-1; x++) {
				if(arr[x] > arr[x+1]) {
					int tmp = arr[x];
					arr[x] = arr[x+1];
					arr[x+1] = tmp;
					flag = true;
				}
			}
		}
		return arr;
	}
}
