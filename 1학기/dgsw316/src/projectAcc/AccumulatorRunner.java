package projectAcc;

import java.util.Scanner;

public class AccumulatorRunner {

	public static void main(String[] args) {
		int input;
		Scanner scanner = new Scanner(System.in);
		Accumulator accu = new Accumulator();

		while (true) {

			System.out.println("������ �Է��ϼ���");
			input = scanner.nextInt();

			if (input == -99) {
				break;
			}

			accu.accAdd(input);
			System.out.println("������ : " + accu.getNumber());
		}

		System.out.println("����");
		scanner.close();
	}
}
