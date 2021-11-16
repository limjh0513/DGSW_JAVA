package kr.hs.dgsw.javaClass.thread;

public class MainClass {
	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		t1.setName("쓰레드 1");
		t1.setPriority(Thread.MAX_PRIORITY); // 우선순위
		t1.start(); // thread를 동시에 수행하기 위해서는 start를 실행시켜야 함

		System.out.println("getID() t1 : " + t1.getId());
		System.out.println("getName() t1 : " + t1.getName());
		System.out.println("isAlive() t1 : " + t1.isAlive()); // Thread가 살아있는지 확인 - isAlive()

		MyRunnable r1 = new MyRunnable();
		// new Thread(r1).start(); // runnable은 이런식으로 start 가능
		Thread t2 = new Thread(r1);
		t2.setPriority(Thread.MIN_PRIORITY);
		t2.start();
		System.out.println("getId() t2 : " + t2.getId());

		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 100; i++) {
			System.out.println("main : " + i);
		}
		
		System.out.println("isAlive() t1 : " + t1.isAlive());
	}
}

//자바는 다중 상속이 불가능하기 때문에 이 방법은 단점이 존재함
