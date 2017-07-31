package test;
//1) Reverse a string without using a temporary variable. DO NO USE REVERSE()
//2) Write a substring method that acceots a string str and in integer idx and 
//returns the substring contained between 0 and idx-1 inclusive. DO NOT use any existing substring metho
public class reverse {
	
	public static String reverseString(String str) {
		if(str.length()<=1)
			return str;
		return reverseString(str.substring(1))+str.charAt(0);
	}
	
	public static String mysubstring(String str, int idx) {
		if(str.equals(""))
			return "You entered an empty string";
		if(idx>str.length())
			return "Your idx isn't bigger than the string length";
		char temp[]=str.toCharArray();
		char newstr[]= new char[str.length()-(str.length()-idx)];
		for(int i=0;i<newstr.length;i++)
			newstr[i]=temp[i];
		return new String(newstr);
	}
	
	public static void main(String args[]) {
		System.out.println(reverseString("Hello World"));
		System.out.println(reverseString(""));
		System.out.println(mysubstring("",5));
		System.out.println(mysubstring("Hello World",5));
		System.out.println(mysubstring("hello",6));
		
	}

}
