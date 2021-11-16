package tr1;

import java.util.Scanner;

public class Polygon {
	protected Scanner sc;
	
	int width;
	int height;
	
	public Polygon(int width, int height) {
//		sc = new Scanner(System.in);
		this.width = width;
		this.height = height;
	}
	
//	protected void closeScanner() {
//		sc.close();
//	}
//	
//	public void input() {
//		
//	}
	
	public String name() {
		return "";
	}
	
	public float result() {
		return (float)0.0;
	}
	
	public void printArea() {
		System.out.println(name() + "의 면적 : " + result());
	}
}
