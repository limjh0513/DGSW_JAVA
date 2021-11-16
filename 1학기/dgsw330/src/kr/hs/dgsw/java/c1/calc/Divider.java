package kr.hs.dgsw.java.c1.calc;

public class Divider extends Adder {

	public Divider(int op1, int op2) {
		super(op1, op2);
	}

	public float calculateDiv() {
		return op1 / op2;
	}

	@Override
	public String getOperator() {
		return "/";
	}


	public static void main(String[] args) {
		Divider div = new Divider(315246, 32);
		div.print();
	}
}
