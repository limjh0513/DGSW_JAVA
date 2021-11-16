package kr.hs.dgsw.java.c1.calc;

public class Multiplier extends Adder {

	public Multiplier(int op1, int op2) {
		super(op1, op2);
	}

	@Override
	public int calculate() {
		return op1 * op2;
	}

	@Override
	public String getOperator() {
		return "*";
	}

	
	public static void main(String[] args) {
		Multiplier mul = new Multiplier(315, 23);
		mul.print();
	}
}
