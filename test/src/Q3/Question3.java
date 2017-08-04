package Q3;

public class Question3 {

	/*
	 * Q3 Reverse a string wihtout using temproray variable
	 */
	public static String reverseString(String str) {
		if(str.length()<=1)
			return str;
		return reverseString(str.substring(1))+str.charAt(0);
	}
	
	public static void main(String[] args) {
		System.out.println(reverseString("Hello World"));
		System.out.println(reverseString(""));
	}

}
