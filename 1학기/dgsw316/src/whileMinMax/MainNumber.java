package whileMinMax;

import java.util.Scanner;

public class MainNumber {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Number number = new Number();
		int put; // 수 입력
		int i = 1;

		while(true) {
			System.out.println(i + "번째 입력");
			put = scanner.nextInt();
			
			if(put == -99) {
				break;
			}

			number.checkNum(put);
			i++;
		}

		System.out.println("입력 종료...");
		System.out.println("최댓값 : " + number.getMax());
		System.out.println("최솟값 : " + number.getMin());
		System.out.println(String.format("평균 : %.3f", number.getAvg()));
		
		scanner.close();
	}

}
