package dgsw608.kr.hs.dgsw.c1.if1;

public class Doer {
	public static void main(String[] args) {
		Dog dog1 = new Dog();
		dog1.eat();
//		
//		Animal dog2 = new Dog();
//		dog2.eat();
		
		Animal animal = new Dog();
		animal.eat();
		animal = new Cat();
		animal.eat();
		
		Sound dog3 = new Dog();
		dog3.makeSound();
		dog1.makeSound();
		
		Japssow spa1 = new Japssow();
		Bird spa2 = new Japssow();
		Animal spa3 = new Japssow();
	}
}
