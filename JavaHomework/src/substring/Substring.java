package substring;

public class Substring {

	public static String substring(String s, int idx) {
		
		StringBuilder sb = new StringBuilder(); 
		
		for (int i = 0 ; i < idx; i++ ) {
			sb.append(s.charAt(i));
		}
		
		return sb.toString();
		
	}
	
	
	public static void main(String[] args) {

		System.out.println(substring("hello", 4));
	}

}
