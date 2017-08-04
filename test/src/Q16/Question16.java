package Q16;

public class Question16 {
	
	/*
	 * Q16 Write a program to display the number of characters for a string input. The string should be entered
	 * as a command line argument using (String[] args)
	 */
	public static void main(String[] args) {
		if(args.length>0) 
			System.out.println("The size of the string in the command line arguements is: " + args[0].length());
		else
			System.out.println("Nothing was passed into command line arguements");

	}

}
