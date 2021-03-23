package kr.hs.dgsw.c1;

public class Local {
	public void method01() {
		int localVariable = 0;

		int a = 7;
		if (0 == localVariable) {
			a = 3;
		} else {
			a = 5;
		}

		System.out.println(a);
	}

	public void method02() {
		int localVariable = 0;
	}
}
