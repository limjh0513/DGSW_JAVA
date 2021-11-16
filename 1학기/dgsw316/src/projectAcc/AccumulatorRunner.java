package projectAcc;

import java.util.Scanner;

public class AccumulatorRunner {

	public static void main(String[] args) {
		int input;
		Scanner scanner = new Scanner(System.in);
		Accumulator accu = new Accumulator();

		while (true) {

			System.out.println("정수를 입력하세요");
			input = scanner.nextInt();

			if (input == -99) {
				break;
			}

			accu.accAdd(input);
			System.out.println("누적값 : " + accu.getNumber());
		}

		System.out.println("종료");
		scanner.close();
	}
}
