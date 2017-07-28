package test;
//1) Reverse a string without using a temporary variable. DO NO USE REVERSE()
//2) Write a substring method that acceots a string str and in integer idx and 
//returns the substring contained between 0 and idx-1 inclusive. DO NOT use any existing substring metho
public class reverse {
	public static void main(String args[]) {
		String str="hello wolrd";
		System.out.println(str);
		char temp=str.charAt(0);
		for(int x=0;x<str.length()/2;x++) {
			temp=str.charAt(x);
			str.replace(str.charAt(x), str.charAt(str.length()-1-x));
			str.replace(str.charAt(str.length()-1-x), temp);
		}
		System.out.println(str);
		String test="ab";
		test.replace('b', 'a');
		System.out.println(test);
	}

}
