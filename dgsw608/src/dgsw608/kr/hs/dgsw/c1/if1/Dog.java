package dgsw608.kr.hs.dgsw.c1.if1;

public class Dog implements Animal, Sound{
	@Override
	public void eat() {
		System.out.println("사료를 먹습니다.");
	}
	
	@Override
	public void makeSound() {
		System.out.println("멍 멍!");
	}
}
