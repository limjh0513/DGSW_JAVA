package kr.hs.dgsw.javaClass.thread;

public class ProAndCon{
	public static void main(String[] args) {
		Object object = new Object();
		
		Producer producer = new Producer(object);
		producer.start();
		
		Consumer c1 = new Consumer(object, producer);
		c1.start();
	}
}
