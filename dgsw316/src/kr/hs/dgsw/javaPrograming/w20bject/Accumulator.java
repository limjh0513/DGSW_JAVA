package kr.hs.dgsw.javaPrograming.w20bject;

public class Accumulator {
	int sum;

	public Accumulator() {
		sum = 0;
		System.out.println("������ ȣ��!");
	}
	
	public Accumulator(int value) {
		sum = value;
	}

	public void add(int value) {
		sum += value;
	}

	public int getSum() {
		return sum;
	}

	public void printResult() {
		System.out.println("��� : " + sum);
	}
}
