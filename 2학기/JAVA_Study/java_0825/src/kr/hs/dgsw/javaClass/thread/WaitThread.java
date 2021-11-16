package kr.hs.dgsw.javaClass.thread;

import java.util.Scanner;

public class WaitThread extends Thread {
	private Object object;
	
	public WaitThread(Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		
		try {
			System.out.println(getId() + " 나를 깨워 줘.");
			synchronized (object) {
				object.wait(); // object의 notify가 호출될 때 까지 멈춤
				//object.wait(3000L);
			}
			System.out.println(getId() + " 아 잘잤다!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Object object = new Object();
		
		WaitThread t1 = new WaitThread(object);
		t1.start();
		
		Scanner sc = new Scanner(System.in);
		
		WaitThread t2 = new WaitThread(object);
		t2.start();
		
		sc.nextLine();
		
		synchronized (object) {
			object.notify(); //끝났다는 것을 알려주기 위함
		}
		
		sc.close();
	}
}
