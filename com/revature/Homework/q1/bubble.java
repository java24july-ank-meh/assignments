package com.revature.Homework.q1;

public class bubble {



public static void main(String[] args) {
	int intArray[] = new int[] {1,0,5,6,3,2,3,7,9,8,4}; // Create initial array
	
	for(int i=0; i < intArray.length; i++){
        System.out.print(intArray[i] + " ");} // print current array
	
	 bubbleSort(intArray);// begin bubble sort
	 
	 System.out.println();// space to print new array
	 
	 for(int i=0; i < intArray.length; i++){
	        System.out.print(intArray[i] + " ");} // print new array
}

private static void bubbleSort(int[] intArray) {
	 int n = intArray.length;
     int temp = 0;
    
     for(int i=0; i < n; i++){
             for(int j=1; j < (n-i); j++){
                    
                     if(intArray[j-1] > intArray[j]){//check if current number in bubble is < current array number, swap them
                             temp = intArray[j-1];
                             intArray[j-1] = intArray[j];
                             intArray[j] = temp;
                     }
                    
             }
     }

}
}



