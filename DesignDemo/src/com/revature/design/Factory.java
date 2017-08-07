package com.revature.design;

public class Factory {
		private Factory() {
			
		}
		
		public static Factory newInstance() {
			return new Factory(); // Return a new object each time
		}

}

