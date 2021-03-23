package numMinMax;

public class Number {
	private int min = 999; // 최소
	private int max = -999; // 최대
	private int sum = 0; // 모든 합
	int i = 0; // 갯수

	public Number() {

	}

	public void checkNum(int put) {
		if (min > put) {
			min = put;
		}
		if (max < put) {
			max = put;
		}
		sum += put;
		i++;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public float getAvg() {
		float result = 0F;
		result = sum / (float) i;

		return result;
	}

}
