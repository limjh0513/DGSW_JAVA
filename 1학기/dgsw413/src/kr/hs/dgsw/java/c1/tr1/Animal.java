package kr.hs.dgsw.java.c1.tr1;

public abstract class Animal {
	// 추상클래스는 body를 가질 수 없음
	
	public abstract String getName();

	public abstract int getCountOfLegs();

	public void print() {
		System.out.println(getName() + " " + getCountOfLegs());
	}
	
	
	public static void main(String[] args) {
		Animal cat = new Cat();
		Animal dog = new Dog();
		
		cat.print();
		dog.print();
	}
}
