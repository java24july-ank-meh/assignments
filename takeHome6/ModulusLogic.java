package revature.takeHome6;

public class ModulusLogic {

	public static void main(String[] args) {
		boolean even = isEven(34);
		System.out.println(even);

		boolean odd = isEven(23);
		System.out.println(odd);
	}
	
	public static boolean isEven(int input){
		int half = input/2;
		if(half*2 == input){
			return true;
		}else{
			return false; 
		}
	}

}
