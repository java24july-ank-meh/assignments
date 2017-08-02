
public class BubbleSort  {
	
	  
	    static void bubbleSort(int[] arr) {  
	       int count= 0 ;  
	       int bubble= arr.length;  
	         for(int x= 0; x < bubble; x ++){  
	                 for(int c2=1; c2 < (bubble- x); c2 ++){  
	                          if(arr[c2- 1] > arr[c2]){  
	                                 //swap elements  
	                                 count = arr[c2-1];  
	                                 arr[c2-1] = arr[c2];  
	                                 arr[c2] = count;  
	             }
	          }
	       }
	    }    
	    public static void main(String[] args) {
		/* bubble sort goes the length of the array to swap values greater than
		 * the current value it is on until the end*/
	    	
		int b[]= {1,0,5,6,3,2,3,7,9,8,4};
		
		System.out.println("Array {1,0,5,6,3,2,3,7,9,8,4} Bubble Sort");  
                System.out.println();  
                bubbleSort(b);  
         
               for(int i=0; i < b.length; i++){  
                System.out.print(b[i] + " ");  
        }  

    }  
}  