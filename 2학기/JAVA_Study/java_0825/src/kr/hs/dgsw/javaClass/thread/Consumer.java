package kr.hs.dgsw.javaClass.thread;
public class Consumer extends Thread {

	private Producer producer;
	private Object object;
	
	public Consumer(Object object, Producer producer) {
		this.producer = producer;
		this.object = object;
	}
	
	@Override
	public void run() {
		while(true) {
			if(producer.items.size() != 0) {
				System.out.println(getId() + " : " + producer.items.get(0));
				producer.items.remove(0);

				if(producer.currentSize == 3 && producer.items.size() == 0) {
					producer.currentSize = 0;
					synchronized (object) {
						object.notify();
					}
				}
			} else {
				synchronized (object) {
					try {
						object.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
