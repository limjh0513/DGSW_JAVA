package projectAcc;

public class Accumulator {
	private int sum;
	
	public Accumulator() {
		sum = 0;
	}
	
	public int getNumber() {
		return sum;
	}
	
	public void accAdd(int number) {
		sum += number;
	}
}
