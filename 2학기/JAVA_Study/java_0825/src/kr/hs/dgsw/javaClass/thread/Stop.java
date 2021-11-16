package kr.hs.dgsw.javaClass.thread;

public class Stop extends Thread {
	@Override
	public void run() {

		while (true) {
			// do something
		
		}

		/*
		 * try { Thread.sleep(Long.MAX_VALUE); } catch (InterruptedException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	public static void main(String[] args) {
		Stop t1 = new Stop();
		System.out.println("쓰레드 시작");
		t1.start();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.interrupt();
		System.out.println("쓰레드 종료");
	}
}
