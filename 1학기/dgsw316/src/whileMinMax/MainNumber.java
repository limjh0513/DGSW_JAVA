package whileMinMax;

import java.util.Scanner;

public class MainNumber {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Number number = new Number();
		int put; // �� �Է�
		int i = 1;

		while(true) {
			System.out.println(i + "��° �Է�");
			put = scanner.nextInt();
			
			if(put == -99) {
				break;
			}

			number.checkNum(put);
			i++;
		}

		System.out.println("�Է� ����...");
		System.out.println("�ִ� : " + number.getMax());
		System.out.println("�ּڰ� : " + number.getMin());
		System.out.println(String.format("��� : %.3f", number.getAvg()));
		
		scanner.close();
	}

}
