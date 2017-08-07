package nora.challenges.solutions.simpleSolutions;

public class PrimeNumbers {

	public static void main(String[] args) {
		int[] nums = new int[100];
		
		//populate the array
		for(int i = 1; i <= 100; i++) {
			nums[i - 1] = i;
		}
		
		printPrimeNumbers(nums);

	}

	private static void printPrimeNumbers(int[] nums) {
		int count = 0;
		
		for(int num : nums) {
			boolean isPrime = true;
			
			//check that all numbers aside from 1 and itself do not return 0 from % operator
			for(int i = num - 1; i > 1; i--) {
				if(num % i == 0) {
					isPrime = false;
					break;
				}
			}
			

			if(isPrime && num != 1) {
				System.out.println(num);
			}
		}
		
		
	}

}
