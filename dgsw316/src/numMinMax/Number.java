package numMinMax;

public class Number {
	private int min = 999; // �ּ�
	private int max = -999; // �ִ�
	private int sum = 0; // ��� ��
	int i = 0; // ����

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
