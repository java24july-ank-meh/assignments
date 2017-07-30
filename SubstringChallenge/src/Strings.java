
public class Strings {

	// Producing a substring without using substring method
	
	public static void main(String[] args) {
		System.out.println (y("IneedToUseMyBrain",10 ));
	}
	public static String y (String s, int j) {
		
		CharSequence y = s;
		y = y.subSequence(5, j);
		return y.toString();
	}

	}	
