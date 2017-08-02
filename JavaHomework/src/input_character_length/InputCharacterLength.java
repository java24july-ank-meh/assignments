package input_character_length;

public class InputCharacterLength {

	public static void printInputCharacterLength(String[] args) {
		
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i] + " length: " + args[i].length());
		}		
	}
	
	
	public static void main(String[] args) {
		printInputCharacterLength(args);
	}

}
