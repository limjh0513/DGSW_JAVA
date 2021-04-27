package study416;

public class Animal {
	String name;
	int legs;
	boolean wings;
	
	public void run() {
		System.out.println(name + "이 달립니다.");
	}
	
	public void eat() {
		System.out.println(name + "이 먹이를 먹습니다.");
	}

	
	public static void main(String[] args) {
		Animal cat = new Animal();
		cat.name = "고양이";
		cat.legs = 4;
		cat.wings = false;
		
		cat.eat();
		System.out.println(cat.name + " " + cat.legs + " " + cat.wings);
		
	}
}
