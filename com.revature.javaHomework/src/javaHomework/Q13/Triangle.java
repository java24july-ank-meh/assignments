package javaHomework.Q13;

//form right triangle out of oscillating 1's and 0's
public class Triangle {

	public static void main(String[] args) {
		
		int x = 10; //amount of levels triangle will have
		int count = 0;
		
		//double for loop forms the right triangle structure
		for (int i = 0; i<x; i++) {
			for (int j = 1; j<i; j++, count++) {
				// count%2 oscillates between 0 and 1 for each inner loop increment
				System.out.print( " "+ (count%2) +" " );
			}
			System.out.println("");
		}

	}

}
