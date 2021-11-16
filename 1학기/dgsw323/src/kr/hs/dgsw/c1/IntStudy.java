package kr.hs.dgsw.c1;

public class IntStudy {
	public static void main(String[] args) {

		System.out.println("size : " + Integer.SIZE);
		System.out.println("bytes : " + Integer.BYTES);
		System.out.println("MIN : " + Integer.MIN_VALUE);
		System.out.println("MAX : " + Integer.MAX_VALUE);

		int a = Integer.MAX_VALUE + 1;
		int b = Integer.MIN_VALUE - 1;
		System.out.println(a);
		System.out.println(b);
	}

}
