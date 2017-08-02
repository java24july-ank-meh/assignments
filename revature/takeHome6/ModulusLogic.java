package revature.takeHome6;

public class ModulusLogic {

	public static void main(String[] args) {
		boolean even = isEven(34);
		System.out.println(even);

		boolean odd = isEven(23);
		System.out.println(odd);
	}
	// modulus is math in itself
	//just do integer division, then multiply back to get the truncated number
	/*
	 * Modulus: A/integer b *b = truncA, % = A-truncA, which would be the remainder
	 */
	public static boolean isEven(int input){
		int half = input/2;
		if(half*2 == input){
			//since we're only dividing by two, should return the same number, but can always find out if A-truncA = 0
			//same logical process
			//else, it'd be smaller
			return true;
		}else{
			return false; 
		}
	}

}
