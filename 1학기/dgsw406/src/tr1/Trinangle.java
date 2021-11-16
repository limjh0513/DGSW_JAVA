package tr1;

import java.util.Scanner;

public class Trinangle extends Polygon {

	public Trinangle(int width, int height) {
		super(width, height);
	}

	@Override
	public float result() {
		return (float) (width * height / 2);
	}

	@Override
	public String name() {
		return "삼각형";
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int width = sc.nextInt();
		int height = sc.nextInt();
		
		Trinangle p1 = new Trinangle(width, height);
		p1.printArea();
		
		sc.close();
	}
}
