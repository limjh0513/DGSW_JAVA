package kr.hs.dgsw.javaPrograming.w20bject;

import java.util.Scanner;

public class ScannerStudy {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("���ڿ� �Է� : ");
			// int number = scanner.nextInt();

			String str = scanner.next();

			if (str.equals("quit")) {
				break;
			}

			System.out.println(str);
		}
		
		scanner.close();
		System.out.println("���α׷� ����");
	}
}
