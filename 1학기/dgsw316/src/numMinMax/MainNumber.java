package numMinMax;

import java.util.Scanner;

public class MainNumber {
	public static final int CNTPUT = 3; // 입력할 횟 수

	public static void main(String[] args) {
		Number number = new Number();
		Scanner scanner = new Scanner(System.in);
		int put; // 수 입력

		for (int i = 1; i <= CNTPUT; i++) {
			System.out.println(i + "번째 입력");
			put = scanner.nextInt();

			number.checkNum(put);
		}

		System.out.println("입력 종료...");
		System.out.println("최댓값 : " + number.getMax());
		System.out.println("최솟값 : " + number.getMin());
		System.out.println(String.format("평균 : %.3f", number.getAvg()));
		
		scanner.close();
	}

}
