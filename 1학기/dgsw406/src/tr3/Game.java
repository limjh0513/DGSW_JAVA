package tr3;

import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		java.util.Random random1 = new java.util.Random();
		int random = random1.nextInt(3);
		// 0 - 가위, 1 - 바위, 2 - 보

		stepCheck(input, random);
	}

	public static void stepCheck(String input, int random) {
		String result = "";
		String randomStr = "";
		if (input.equals("가위")) {
			result = scissors(random);
			randomStr = changeStr(random);
			resultPrint(result, randomStr);
		} else if (input.equals("바위")) {
			result = rock(random);
			randomStr = changeStr(random);
			resultPrint(result, randomStr);
		} else if (input.equals("보")) {
			result = paper(random);
			randomStr = changeStr(random);
			resultPrint(result, randomStr);
		} else {
			System.out.println("잘못된 값을 입력했습니다!");
		}
	}

	public static void resultPrint(String str, String randomStr) {
		System.out.println("결과 : " + str + "\n" + "상대가 낸 것 : " + randomStr);
	}

	public static String changeStr(int random) {
		switch (random) {
		case 0:
			return "가위";
		case 1:
			return "바위";
		case 2:
			return "보";
		}
		return null;
	}

	public static String rock(int random) {
		switch (random) {
		case 0:
			return "승리";
		case 1:
			return "무승부";
		case 2:
			return "패배";
		}
		return null;
	}

	public static String scissors(int random) {
		switch (random) {
		case 0:
			return "무승부";
		case 1:
			return "패배";
		case 2:
			return "승리";
		}
		return null;

	}

	public static String paper(int random) {
		switch (random) {
		case 0:
			return "패배";
		case 1:
			return "승리";
		case 2:
			return "무승부";
		}
		return null;
	}
}
