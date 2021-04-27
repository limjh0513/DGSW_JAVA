package kr.hs.dgsw.java.c1.tr1.poly.Array;

import java.util.Random;

public class ArraySudey {
	public static void main(String[] args) {
		int[] intArray = new int[5];
		double[] doubleArray = new double[7];
		Cat[] catArray = new Cat[10];

//		System.out.println(intArray[1]);
//		intArray[0] = 5;
//		intArray[2] = 4;
//		System.out.println(intArray[0]);
//		System.out.println(intArray[4]);

		Random random = new Random();

		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = random.nextInt(1000);
		}
		for (int value : intArray) {
			System.out.println(value);
		}
	}
}
