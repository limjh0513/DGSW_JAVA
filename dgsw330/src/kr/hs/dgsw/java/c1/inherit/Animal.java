package kr.hs.dgsw.java.c1.inherit;

public class Animal {
	private String name;
	private String food;

	public void eat() {
		System.out.println(name + "이(가) " + food + "을(를) " + "먹습니다.");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getFood() {
		return food;
	}

	public static void main(String[] args) {
		Animal lion = new Animal();
		Animal hipo = new Animal();

		lion.setName("사자");
		lion.setFood("고기");
		hipo.setName("하마");
		hipo.setFood("풀");

		lion.eat();
		hipo.eat();
		
		Animal snake = new Reptile();
		Animal parrot = new Birds();
		
		//parrot.fly(); 불가!
		

	}
}
