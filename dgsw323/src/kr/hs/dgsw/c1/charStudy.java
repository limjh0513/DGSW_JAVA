package kr.hs.dgsw.c1;

public class charStudy {
	public static void main(String[] args) {
		char a = 65;
//		char b = '��';
		char ba = '��';
		char bb = '��';
		System.out.println(a);
		System.out.println(bb - ba);

		for (char i = ba; i <= bb; i++) {
			System.out.println(i);
		}

		int count = '�R' - '��' + 1;
		System.out.println(count);
	}
}
