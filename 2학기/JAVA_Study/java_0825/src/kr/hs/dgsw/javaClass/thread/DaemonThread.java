package kr.hs.dgsw.javaClass.thread;

public class DaemonThread extends Thread {
	@Override
	public void run() {
		/*
		 * for (int i = 0; i < 100; i++) { System.out.println(getId() + " : " + i); }
		 */
		
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) { // DaemonThread -> 부모 Thread가 죽으면 Thread도 같이 죽음 -> 자원의 낭비가 사라짐
		DaemonThread t1 = new DaemonThread();
		DaemonThread t2 = new DaemonThread();

		t1.setDaemon(true);
		t2.setDaemon(false);

		t1.start();
		//t2.start();
	}
}
