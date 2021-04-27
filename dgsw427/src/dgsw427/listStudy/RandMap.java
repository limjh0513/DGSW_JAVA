package dgsw427.listStudy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandMap {
	private static final int SIZE = 1000000;

	public static long testListPerformance(List<Integer> list) {
		for (int i = 0; i < SIZE; i++) {
			list.add(random.nextInt());
		}
		long before = System.currentTimeMillis();
		list.get(random.nextInt(SIZE));

		long after = System.currentTimeMillis();

		return after - before;
	}

	public static long testMapPerformance(Map<Integer, Integer> map) {
		for (int i = 0; i < SIZE; i++) {
			map.put(i, random.nextInt());
		}
		long before = System.currentTimeMillis();
		map.get(random.nextInt(SIZE));

		long after = System.currentTimeMillis();

		return after - before;
	}

	private static Random random = new Random();

	public static void main(String[] args) {
		Map<Integer, Integer> randMap = new HashMap<Integer, Integer>();
		List<Integer> randList = new LinkedList<Integer>();

		System.out.println(testListPerformance(randList));
		System.out.println(testMapPerformance(randMap));
	}

}
