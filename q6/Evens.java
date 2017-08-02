package q6;

import java.util.*;

public class Evens {
	public static void main(String args[])
	  {
	    int c;
	    System.out.println("Input an integer");
	    Scanner input = new Scanner(System.in);
	    int x = input.nextInt();
	 
	    if ( (x/2)*2 == x )//without changing types, decimals are rounded down to integers. So if the number is odd, it won't equal the starting number multiplied by 2
	      System.out.println("Even");
	    else
	      System.out.println("Odd");
	  }
}
