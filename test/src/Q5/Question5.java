package Q5;

public class Question5 {
	
	public final static float yi=1.1f;
	public final static float er=2.22f;
	
	/*
	 * Q5 Write a substring method that accepts a string str and an integer idx and 
	 * returns the substring contained between 0 and idx-1 invlusive.
	 */
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
		System.out.println(mysubstring("",5));
		System.out.println(mysubstring("Hello World",5));
		System.out.println(mysubstring("hello",6));
		
	}
}
