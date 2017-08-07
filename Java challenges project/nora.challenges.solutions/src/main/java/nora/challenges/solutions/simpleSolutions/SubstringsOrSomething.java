package nora.challenges.solutions.simpleSolutions;

public class SubstringsOrSomething {

	public static void main(String[] args) {
		String test1 = "My first test";

		System.out.println(subMe(test1, 9));

	}
	
	public static String subMe(String str, int idx) {
		char[] stuff = str.toCharArray();
		String temp = "";
		
		for(int i = 0; i < idx - 1; i++) {
			temp += stuff[i];
		}
		return temp;
	}

}
