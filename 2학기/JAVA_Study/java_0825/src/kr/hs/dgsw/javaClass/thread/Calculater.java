package kr.hs.dgsw.javaClass.thread;

public class Calculater {
	private int sum = 0;

	public void add(int value) { // synchronized를 사용하면 쓰레드가 접근 할 때 쓰레드 1개가 다 수행하고 나서 다른 쓰레드가 들어올 수 있도록 함
		// 아주 많은 일을 한다

		synchronized (this) { // 이 부분만 synchronized가 필요할 때에는 이런 식으로 사용
			sum += value;
		}
		// 무지 많은 일을 한다
	}

	public int getSum() {
		return sum;
	}
}
