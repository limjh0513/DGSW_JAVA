package kr.hs.dgsw.java.c1.tr1.poly.Array;

public class Queue {
	private static final int MAX = 100;
	private String[] queue = new String[MAX];

	int head = -1;
	int tail = -1;

	public void add(String str) { // 스택 값 삽입
		queue[++head] = str;
		if (head >= 0 && tail < 0) {
			tail = 0;
		}
	}

	public String get() { // 스택 값 빼기
		if (head - tail >= 0) {
			return queue[tail++];
		} else {
			System.out.println("값이 존재하지 않습니다.");
			return null;
		}
	}

	public static void main(String[] args) {
		Queue queue = new Queue();
		queue.add("대구");
		queue.add("서울");
		queue.add("제주");
		queue.add("울산");

		
		System.out.println(queue.get());
		System.out.println(queue.get());
		System.out.println(queue.get());
		System.out.println(queue.get());
		System.out.println(queue.get());
		
	}

}
