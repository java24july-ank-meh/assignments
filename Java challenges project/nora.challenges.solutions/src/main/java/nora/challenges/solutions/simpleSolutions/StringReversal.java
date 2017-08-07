package nora.challenges.solutions.simpleSolutions;

public class StringReversal {

	public static void main(String[] args) {
		System.out.println(revNoTemp("A string that must be reversed."));

	}
	
	public static String revNoTemp(String revMe) {
		int length = revMe.length();
		for(int i = 0; i < length; i++) {
			
			//char temp = revMe.charAt(length - 1);
			
			//revMe.substring(0, length - 1); //take away the last character
			//revMe.substring(0, i + 1); //the part of the string that has already been reversed
			//revMe.substring(i + 1, length - 1); //the rest of the string minus the part of the string we are moving
			revMe = revMe.substring(0, i) + revMe.charAt(length - 1) + revMe.substring(i, length - 1);
		}
		return revMe;
	}

}
