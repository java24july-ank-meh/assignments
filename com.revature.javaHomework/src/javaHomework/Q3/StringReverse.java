package javaHomework.Q3;

public class StringReverse {
	private static void customReverse(String str) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = str.length() -1; i>-1; i--){
				sb.append(str.charAt(i));
		}
		
		System.out.println(sb.toString()); 
	}	
	
	public static void main(String[] args) {
		String s1 = new String("racecar1");
		
		customReverse(s1);
	}
}
