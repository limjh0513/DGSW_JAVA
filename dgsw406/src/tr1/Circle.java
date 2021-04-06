package tr1;

import java.util.Scanner;

public class Circle extends Polygon {

	public Circle(int r1, int r2) {
		super(r1, r2);
	}

	@Override
	public float result() {
		return (float) (width * height * Math.PI);
	}

	@Override
	public String name() {
		return "원";
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r1 = sc.nextInt();
		int r2 = sc.nextInt();
		
		Circle p1 = new Circle(r1, r2);
		p1.printArea();
		sc.close();
	}
}
