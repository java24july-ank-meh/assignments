package print_even;

import java.util.ArrayList;

public class PrintEven {

	public static void printEven(ArrayList<Integer> list) {
		
		for (Integer i : list) {
			if ((i % 2) == 0) {
				System.out.println(i);
			}
		}
	}
	
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		
		for ( int i = 1; i <= 100; i++) {
			list.add(i); 
		}
		
		printEven(list);
		
	}
}
