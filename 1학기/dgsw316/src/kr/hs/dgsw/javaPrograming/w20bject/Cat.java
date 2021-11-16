package kr.hs.dgsw.javaPrograming.w20bject;

public class Cat {
	public static void main(String[] args) {
		Cat nabi = new Cat();
		Cat nero = new Cat();

		nabi.color = "흰색";
		nabi.name = "나비";
		nabi.colorOfEyes = "초록색";

		nero.color = "검정색";
		nero.name = "네로";
		nero.colorOfEyes = "갈색";
	}

	String color;
	String name;
	String colorOfEyes;

	public void sleep() {

	}

	public void punch() {

	}

}
