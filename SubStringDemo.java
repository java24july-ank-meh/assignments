package ClassDemoTwo;

public class SubStringDemo {

	public static void main(String[] args) {
		//StringBuilder sb = new StringBuilder();
		//2) Write a substring method that accepts a string str and in integer idx
		// and returns the substring contained between 0 and idx-1 inclusive. DO NOT
		// use any existing substring methods in String, ..
String max = "max turner";

System.out.println(subString(max,max.length()-1));


	}
	/*Use private static access modifiers that way the code can be accessed
	 * only within its class and not from outside parties. 
	 * */
	private static StringBuilder subString(String str, int idx) {
	StringBuilder sb = new StringBuilder();
	
	for (int i = 0; i <= idx; i++) {
	sb.append(str.charAt(i));
	}
	
		
		
		return sb ;
	}
}


	

