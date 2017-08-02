package revature.takeHome15;

public class SmoothOperation implements Mathers{
	public SmoothOperation(){
		
	}
	//interface gave us the rules, so now we address and define them here
	//very simple operator standards
	//now have smooth operator stuck in head
	@Override
	public int addition(int n, int o) {
		return n + o;
	}

	@Override
	public int subtraction(int n, int o) {
		return n-o;
	}

	@Override
	public int multiplication(int n, int o) {
		return n*o;
	}

	@Override
	public int division(int n, int o) {
		return n/o;
	}

}
