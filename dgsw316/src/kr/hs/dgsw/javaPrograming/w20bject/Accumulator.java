package kr.hs.dgsw.javaPrograming.w20bject;

public class Accumulator {
	int sum;

	public Accumulator() {
		sum = 0;
		System.out.println("생성자 호출!");
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
		System.out.println("결과 : " + sum);
	}
}
