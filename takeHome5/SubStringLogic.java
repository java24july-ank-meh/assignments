package revature.takeHome5;

public class SubStringLogic {
	public static void main(String[] args) {
		String other = subString("shorten", 3);
		System.out.println(other);
	}

	public static String subString(String str, int idx) {
		StringBuilder build = new StringBuilder("");

		for (int i = 0; i <= idx - 1; i++) {
			build.append(str.charAt(i));
		}
		// String retString = build.toString();
		return build.toString();
	}
}
