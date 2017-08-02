package bubble_sort;

import java.util.ArrayList;
import java.util.Arrays;

public class BubbleSort {

	public static ArrayList<Integer> bubble_sort(ArrayList<Integer> lst) {
		
		for (int i = lst.size() - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (lst.get(j) > lst.get(j + 1)) {
					Integer temp = lst.get(j); 
					lst.set(j, lst.get(j + 1));
					lst.set(j + 1, temp);
				}
			}
		}
		
		return lst;
	}
	
	
	public static void main(String[] args) {

		System.out.println("Testing Bubble Sort");
		
		ArrayList<Integer> lst = new ArrayList<>(Arrays.asList(3,2,1));
		
		System.out.println(bubble_sort(lst));
		
		
		System.out.println("Done Testing Bubble Sort");
				
	}
	

}
