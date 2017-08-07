package question03;

public class Q3 {
	/*
	 * Question 3: reverse a string without using a temporary
	 * variable. Do NOT use reverse() in the StringBuffer or the
	 * StringBuilder API.
	 */
	public static void main(String[] args) {
		//Q3 Test
		System.out.println("Test\nhello -> "+reverse("hello"));
	}
	public static String reverse (String string) {
		StringBuilder sb = new StringBuilder();
		for(int x = string.length()-1; x >= 0; x--) {
			sb.append(string.charAt(x));
		}
		return sb.toString();
	}
}
