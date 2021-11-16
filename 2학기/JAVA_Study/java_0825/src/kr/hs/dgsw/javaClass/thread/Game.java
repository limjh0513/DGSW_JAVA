package kr.hs.dgsw.javaClass.thread;

import java.util.Scanner;

public class Game implements Runnable {
	private String name;

	public Game(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 25000; i++) {
			System.out.println(name + " : " + i);
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String u1 = sc.nextLine();
		String u2 = sc.nextLine();
		
		Game g1 = new Game(u1);
		Game g2 = new Game(u2);
		new Thread(g1).start();
		new Thread(g2).start();
		
		System.out.println("게임 종료");
	}

}
