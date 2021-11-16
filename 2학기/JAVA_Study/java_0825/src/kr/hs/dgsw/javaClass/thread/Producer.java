package kr.hs.dgsw.javaClass.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer extends Thread {
	private Object object;
	List<Integer> items = new ArrayList<Integer>();
	int currentSize = 0;
	boolean isEmpty = false;

	public Producer(Object object) {
		this.object = object;
	}

	@Override
	public void run() {
		Random random = new Random(System.currentTimeMillis());

		while (true) {
			int i = random.nextInt(100);
			if (currentSize >= 3) {
				synchronized (object) {
					try {
						object.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("생성 : " + i);
			items.add(i);
			currentSize++;
			synchronized (object) {
				object.notify();
			}
		}
	}
}
