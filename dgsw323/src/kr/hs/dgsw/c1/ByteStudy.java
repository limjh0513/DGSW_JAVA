package kr.hs.dgsw.c1;

import java.util.Scanner;

public class ByteStudy {

	public static void main(String[] args) {
//		System.out.println(Byte.SIZE + " " + Byte.BYTES);
//		
//		byte byteValue = 15;
//		System.out.println();

		Scanner sc = new Scanner(System.in);

		byte byteValue = sc.nextByte();
		byte copy = byteValue;
		String str = conver(byteValue);

		//System.out.println(byteValue + " -> " + str);
		System.out.println(byteValue + " -> " + "0x"+str);
		System.out.println(String.format("%d -> 0x%X", byteValue, byteValue));
		sc.close();
	}

	private static String conver(byte i) {
		String result = "";

		String highStr;
		String lowStr;

		int high = i / 16;
		int low = i % 16;

		System.out.println(high + " " + low);

		if (high >= 10) {
			highStr = toHex(high);
		} else {
			highStr = Integer.toString(high);
		}

		if (low >= 10) {
			lowStr = toHex(low);
		} else {

			lowStr = Integer.toString(low);
		}

		if (high == 0) {
			result = lowStr;
		} else {
			result = highStr + lowStr;
		}

		return result;

	}
	
//	private static String conver(byte i) {
//		String result = "";
//
//		String highStr;
//		String lowStr;
//
//		int high = i / 16;
//		int low = i % 16;
//		
//		highStr = toHex(high);
//		lowStr = toHex(low);
//		
//		result = highStr + lowStr;
//
//		return result;
//
//	}

	private static String toHex(int i) {
		switch (i) {
		case 10:
			return "A";
		case 11:
			return "B";
		case 12:
			return "C";
		case 13:
			return "D";
		case 14:
			return "E";
		case 15:
			return "F";
		default:
			return null;
			//return i + "";
		}
	}
}
