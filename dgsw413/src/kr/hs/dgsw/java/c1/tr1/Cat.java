package kr.hs.dgsw.java.c1.tr1;

public class Cat extends Animal{
	//추상 메소드 사용시 클래스는 추상 클래스여야 함!!!
	@Override
	public String getName() {
		return "고양이";
	}
	
	@Override
	public int getCountOfLegs() {
		return 4;
	}
	
	public static void main(String[] args) {
		Animal cat = new Cat();
		System.out.println(cat.getName());
	}

}
