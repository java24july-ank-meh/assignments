package question15;

public class Calculator implements IQ15{
	private int a;
	private int b;
	
	public Calculator() {}
	public Calculator(int n, int m) {
		a = n;
		b = m;
	}
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	
	@Override
	public int addition() {
		return a+b;
	}

	@Override
	public int substration() {
		return a-b;
	}

	@Override
	public int multiplication() {
		return a*b;
	}

	@Override
	public int division() {
		return a/b;
	}
}
