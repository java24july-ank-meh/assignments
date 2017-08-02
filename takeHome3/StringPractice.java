package revature.takeHome3;

public class StringPractice {

	public static void main(String[] args) {
		String newWord = stringReverse("mario");
		System.out.println(newWord);

	}

	public static String stringReverse(String twist) {
		StringBuilder build = new StringBuilder("");
		for (int i = twist.length() - 1; i >= 0; i--) {
			build.append(twist.charAt(i));
		}
		return build.toString();
		// didn't use reverse, but still needed StringBuilder to be able to
		// "build up" the String first
		// possible swap , but would prefer using StringBuidler to avoid math
	}

}
