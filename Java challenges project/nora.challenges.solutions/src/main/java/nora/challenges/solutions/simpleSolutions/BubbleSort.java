package nora.challenges.solutions.simpleSolutions;

public class BubbleSort {

	public static void main(String[] args) {
		int[] nums1 = {1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4};
		System.out.println("Before:");
		printNums(nums1);
		sortWithBubbles(nums1);
		System.out.println("After:");
		printNums(nums1);

	}


	/*Goes through the entire array and swaps adjacent numbers that are close to one another
	 * This repeats till no more swaps are needed.*/
	private static void sortWithBubbles(int[] nums) {
		while(true) {
			boolean hasSwapped = false;
			
			for(int i = 0; i < nums.length; i++) {
				if(i != nums.length - 1 && nums[i] > nums[i+1]) {
					hasSwapped = true;
					//swapping time
					int temp = nums[i];
					nums[i] = nums[i+1];
					nums[i+1] = temp;
				}
			}
			
			if(!hasSwapped) return;
		}//while loop
		
	}

	private static void printNums(int[] nums) {
		for(int num : nums) System.out.print(num + " ");
		System.out.println();
		
	}
}
