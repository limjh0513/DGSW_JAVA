package kr.hs.dgsw.java.c1.tr1.poly.Array;

import java.util.Arrays;
import java.util.Random;

public class Sort {
	private static final int SIZE = 100;

	int[] array = new int[SIZE];

	public void fillValues() {
		Random random = new Random();

		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(10000);
		}
	}

	public void sort() {
		// Arrays.sort(array);

		for (int i = 0; i < array.length; i++) {
			for(int j = 0; j < i; j++) {
				if(array[i] < array[j]) {
					int step = array[i];
					array[i] = array[j];
					array[j] = step;
				}
			}
		}
	}

	public void print() {
		for (int value : array) {
			System.out.println(value);
		}
	}

	public static void main(String[] args) {
		Sort sort = new Sort();

		sort.fillValues();
		System.out.println("정렬 전");
		sort.print();
		sort.sort();
		System.out.println("\n\n\n\n정렬 후");
		sort.print();

	}
}
