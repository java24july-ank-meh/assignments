package nora.challenges.solutions.simpleSolutions;

public class EvensAndOdds {

	public static void main(String[] args) {
		System.out.println("is 5 even: " + isEven(5));
		System.out.println("is 100 even: " + isEven(100));
		System.out.println("is -75 even: " + isEven(-75));	

	}
	
	private static boolean isEven(int num) {
		//This method only works if the number is positive, so we ensure it is positive to start
    	if(num < 0) num *= -1;
    	
    	//go through and subtract 2 from the number till it is either 1 or 0
    	while(num > 1) {
    		num -= 2;
    	}
    	
    	//it will be 0 if it was even and 1 if it was odd
    	return num == 0;
    }

}
