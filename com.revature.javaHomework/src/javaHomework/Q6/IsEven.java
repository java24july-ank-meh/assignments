package javaHomework.Q6;

//function to check if integer is even
public class IsEven {
	
	public static boolean isEven(int i) {
		return ((i & 1) == 0);
		/*
		 * bitwise and operation checks if smallest digit of the binary two's complement
		 * representation of the integer i is a 1 or a zero (odd or even)
		 */
	}

	public static void main(String[] args) {

		int[] arr = { 1, 0, 5, 6, 3, 2, 7, 9, 8, 4 };

		for (int i = 0; i < arr.length; i++) {

			if (isEven(arr[i])) {
				System.out.println(arr[i] + " is even");
			} else {
				System.out.println(arr[i] + " is odd");
			}
		}

	}

}
