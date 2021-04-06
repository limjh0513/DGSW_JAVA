package kr.hs.dgsw.java.c1.calc;

public class Adder {

	protected int op1;
	protected int op2;

	public Adder(int op1, int op2) {
		this.op1 = op1;
		this.op2 = op2;
	}

	public int calculate() {
		return op1 + op2;
	}

	public String getOperator() {
		return "+";
	}

	public void print() {
		String str = String.format("%d %s %d = %d", op1, getOperator(), op2, calculate());
		System.out.println(str);
	}

	public static void main(String[] args) {
		Adder adder = new Adder(3224, 7549);
		adder.print();
	}

}
