

 public class Homework1{
	
	// PART 1:
	private static void customReverse(String str) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = str.length() -1; i>-1; i--){
				sb.append(str.charAt(i));
		}
		
		System.out.println(sb.toString()); 
	}	
	
	// PART 2:
	private static void subString(String str, int idx){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<idx; i++){
			sb.append(str.charAt(i));
		}
		
		System.out.println(sb.toString()); 
	}
	
	
	public static void main(String[] args) {
			String s1 = new String("racecar1");
			
			customReverse(s1);
			subString(s1,4);
	}
}

 
 

