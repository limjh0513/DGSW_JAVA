package numMinMax;

import java.util.Scanner;

public class MainNumber {
	public static final int CNTPUT = 3; // �Է��� Ƚ ��

	public static void main(String[] args) {
		Number number = new Number();
		Scanner scanner = new Scanner(System.in);
		int put; // �� �Է�

		for (int i = 1; i <= CNTPUT; i++) {
			System.out.println(i + "��° �Է�");
			put = scanner.nextInt();

			number.checkNum(put);
		}

		System.out.println("�Է� ����...");
		System.out.println("�ִ� : " + number.getMax());
		System.out.println("�ּڰ� : " + number.getMin());
		System.out.println(String.format("��� : %.3f", number.getAvg()));
		
		scanner.close();
	}

}
