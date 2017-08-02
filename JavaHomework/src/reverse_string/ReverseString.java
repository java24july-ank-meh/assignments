package reverse_string;

public class ReverseString {

	public static String reverse(String s) {
		
		if (s.length() <= 0) {
			return "";
		}

		StringBuilder str = new StringBuilder();
			
		for (int i = s.length() - 1; i >= 0; i--) {
			str.append(s.charAt(i));
		}
			
		return str.toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println(reverse("hello"));
		
	}
	
}
