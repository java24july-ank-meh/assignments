package revature.takeHome12;

public class ForEachPractice {
	public static void main(String[] args){
		int[] numList = new int[100];
		for(int i = 0; i<= 99; i++){
			numList[i] = i+1;
		}
		
		for(int i : numList){
			if(i%2 ==0){
				System.out.println(i);
			}
		}
	}
}
