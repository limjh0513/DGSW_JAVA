package tr1;

import java.util.Scanner;

public class Rectangle extends Polygon{

	public Rectangle(int width, int height) {
		super(width, height);
	}
	
	@Override
	public float result() {
		return (float)(width * height);
	}
	
	@Override
	public String name() {
		 return "사각형";
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int width = sc.nextInt();
		int height = sc.nextInt();
		
		Rectangle p1 = new Rectangle(width, height);
		p1.printArea();
		sc.close();
	}

}
