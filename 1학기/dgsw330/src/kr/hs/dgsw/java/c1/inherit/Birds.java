package kr.hs.dgsw.java.c1.inherit;

public class Birds extends Animal {
	public void fly() {
		System.out.println(getName() + "이(가) " + "하늘을 날다");
	}
	
	public static void main(String[] args) {
		Birds passer = new Birds();
		passer.setName("참새");
		passer.setFood("곡물");
		passer.eat();
		passer.fly();
	}
}
