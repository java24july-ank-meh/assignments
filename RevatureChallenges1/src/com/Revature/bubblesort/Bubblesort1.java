package com.Revature.bubblesort;

import java.util.Arrays;

public class Bubblesort1 {
	//array is pass by reference, no need to return anything.
	public void sort(int[] x){
		int temp1, temp2;
		//every loop goes through one less element at the end, no need if array is 1 or 0 in length.
		if(x.length < 2){
			return;
		}
		for(int i=x.length;i>0;--i){
			for(int j=0; j<i-1;j++){
				temp1 = x[j];
				temp2 = x[j+1];
				//swap if first is larger between the two.
				if(temp1>temp2){
					x[j+1] = temp1;
					x[j] = temp2;
				}
			}
		}
	}
}
