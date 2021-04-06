package kr.hs.dgsw.java.c1.calc;

public class Subtractor extends Adder {

	public Subtractor(int op1, int op2) {
		super(op1, op2);
	}

	
	@Override
	public int calculate() {
		return op1 - op2;
	}

	@Override
	public String getOperator() {
		return "-";
	}
	
	public static void main(String[] args) {
		Subtractor sub = new Subtractor(9842, 4323);
		sub.print();
	}

}
