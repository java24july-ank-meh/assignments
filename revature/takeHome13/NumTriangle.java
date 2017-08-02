package revature.takeHome13;

import java.util.ArrayList;
import java.util.Arrays;

public class NumTriangle {
	public static void main(String[] args){
		triangle(4);
		//triangle(8);
	}
	public static void triangle(int iter){
		ArrayList<Integer> triBar = new ArrayList<Integer>();
		//String check = "";
		for(int i = 0; i<iter; i++){
			if(i%4 ==0){
				triBar.add(0);
				System.out.println(Arrays.toString(triBar.toArray()));
				//System.out.println("0 added at end");
			}else if(i%4 == 1){
				triBar.add(0, 1);
				System.out.println(Arrays.toString(triBar.toArray()));
				//System.out.println("1 added at beginning"); 
			}else if(i%4 == 2 ){
				triBar.add(1);
				System.out.println(Arrays.toString(triBar.toArray()));
				//System.out.println("1 added at end");
			}else if(i%4== 3){
				triBar.add(0, 0);
				//System.out.println("0 added at beginning");
				System.out.println(Arrays.toString(triBar.toArray()));
			}
		}
		
	}
}

