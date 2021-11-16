package kr.hs.dgsw.javaClass.thread;

public class SyncThread extends Thread {

	private int value;
	private Calculater calculater;

	public SyncThread(int value, Calculater calculater) {
		this.value = value;
		this.calculater = calculater;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			calculater.add(value);
		}
		
		System.out.println(getId() + " 종료 - " + System.currentTimeMillis());
	}
	
	public static void main(String[] args) {
		Calculater calculater = new Calculater();
		SyncThread t1 = new SyncThread(1, calculater);
		t1.start();
		SyncThread t2 = new SyncThread(-1, calculater);
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
		System.out.println("Sum : " + calculater.getSum());
	}
}
