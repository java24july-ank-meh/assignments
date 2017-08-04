package javaHomework.Q5;

public class Substring {
	private static void subString(String str, int idx){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<idx; i++){
			sb.append(str.charAt(i));
		}
		
		System.out.println(sb.toString()); 
	}
	
	
	public static void main(String[] args) {
			String s1 = new String("racecar1");
			
			subString(s1,4);
	}
}
