package caculater;

import java.util.Scanner;

public class Caculater {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n1 = scanner.nextInt();
		int n2 = scanner.nextInt();

		System.out.println(sum(n1, n2));
		System.out.println(min(n1, n2));
		System.out.println(mul(n1, n2));
		System.out.println(div(n1, n2));

	}

	public static int sum(int n1, int n2) {
		return n1 + n2;
	}

	public static int min(int n1, int n2) {
		return n1 - n2;
	}

	public static int mul(int n1, int n2) {
		return n1 * n2;
	}

	public static int div(int n1, int n2) {
		if (n1 > n2) {
			return n1 / n2;
		} else {
			return n2 / n1;
		}
	}
}
