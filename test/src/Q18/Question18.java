package Q18;

/*
 * Q18 Write a program having a concrete subclass that inherits three abstract methods 
 * from a superclass. Provide the following three implementations in the subclass 
 * corresponding to the abstract methods in the superclass:
 * 		1. Check for uppercase characters in a string, and return 'true' or 'false'
 * 			depending if any are found
 * 		2. Convert all of the lower case characters to uppercase in the input string, and
 * 			return the result.
 * 		3. Convert the input string to integer and add 10, output the result to the 
 * 			console.
 */
public class Question18 extends Question18Abstract{
	
	public boolean checkForUpperCase(String str) {
		char[] arr=str.toCharArray();
		for(int i=0;i<arr.length;i++)
			if(Character.isUpperCase(arr[i]))
				return true;
		return false;
	}
	
	public String convertLowersToUppers(String str) {
		return str.toUpperCase();
	}
	
	public int convertStringToInteger(String str) {
		try {
			int i=Integer.parseInt(str);
			return i+10;
		}
		catch (NumberFormatException e){
			e.getMessage();
		}
		return -1;
	}

	public static void main(String[] args) {
		Question18 q= new Question18();
		System.out.println(q.checkForUpperCase("hello world!"));
		System.out.println(q.convertLowersToUppers("hi i'm paul %^&*( F|g"));
		System.out.println(q.convertStringToInteger("12"));

	}

}

abstract class Question18Abstract{
	public abstract boolean checkForUpperCase(String str);
	public abstract String convertLowersToUppers(String str);
	public abstract int convertStringToInteger(String str);
}
