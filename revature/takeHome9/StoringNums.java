package revature.takeHome9;

import java.util.ArrayList;

public class StoringNums {
	public static void main(String[] args){
			ArrayList<Integer> numberList = new ArrayList<Integer>();
			for(int i = 1; i<=100; i++){
				numberList.add(i);
			}
			//System.out.println(numberList.get(1));
			
			int counter = 0;
			
			for(int i : numberList){
				boolean isPrime = true;
				
				for(int j = counter; j>1; j--){
					
					
					if(i%j == 0){
						isPrime = false;
						break;
					}
					
					//counter++;
				}
				if(isPrime && i!=1){
					System.out.println(i);
				}
				counter++;
			}
			
	}
	
	
}
