package kr.hs.dgsw.c1;

public class VariableLab {
	int intValue;
	static String strValue;

	public static void main(String[] args) {
		VariableLab lab1 = new VariableLab();
		VariableLab lab2 = new VariableLab();

		lab1.intValue = 5;
		lab2.intValue = 10;

//		lab1.strValue = "Hello";
//		lab2.strValue = "World";
		VariableLab.strValue = "Hello";
		VariableLab.strValue = "World";

		System.out.println(lab1.intValue + " " + lab2.intValue);
		System.out.println(lab1.strValue + " " + lab2.strValue);

	}
}
