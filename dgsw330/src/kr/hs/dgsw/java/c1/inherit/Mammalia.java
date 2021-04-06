package kr.hs.dgsw.java.c1.inherit;

public class Mammalia extends Animal{
	
	public void nursebBary() {
		System.out.println("새끼에게 젖을 먹인다");
	}
	
	@Override
	public void eat() {
		System.out.println("애기는 젖을 먹는다.");
		super.eat();
	}
	
	public void eat(String food) {
		System.out.println("....");
	}
	
	
//한 클래스 안에 같은 이름의 함수가 있는 것이 overloading
//부모와 자식의 메소드가 같은 것을 overriding
	
	public static void main(String[] args) {
		Mammalia human = new Mammalia();
		Animal dog = new Mammalia(); //허용 -> eat을 사용하면 Mammalia의 eat을 사용함!!!(다형성)
//		Mammalia mouse = new Animal(); 비허용
//		Birds hen = new Mammalia(); 비허용
		Animal snake = new Animal();
		human.setName("사람");
		human.setFood("이것저것");
		
		human.eat();
		
		dog.setName("강아지");
		dog.setFood("사료");
		dog.eat(); //Mammlia eat을 가져옴
	}
}
