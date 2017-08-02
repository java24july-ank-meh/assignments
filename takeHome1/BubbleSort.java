package revature.takeHome1;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] disorg = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		Bubble(disorg);

	}

	// swap will help keep up with swapped numbers, moving the index along, which will
	// stop the for loop before it reaches out of bounds
	public static int[] Bubble(int[] disorg) {
		boolean swap = true;
		int swapInd = 0;
		int switcher;
		System.out.println(Arrays.toString(disorg));
		while (swap) {
			swap = false;
			swapInd++;
			for (int i = 0; i < disorg.length - swapInd; i++) {
				if (disorg[i] > disorg[i + 1]) {
					switcher = disorg[i];
					disorg[i] = disorg[i + 1];
					disorg[i + 1] = switcher;
					swap = true;
					// assigning value with a temp (shown with switcher) to
					// avoid having two similarly assigned values
					System.out.println(Arrays.toString(disorg));
					// just to show the process as it goes along
				}
			}
		}

		return disorg;
	}
}
