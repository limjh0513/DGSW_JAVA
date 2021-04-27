package dgsw427.listStudy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandArray {
	
	public static void getTime(List<Integer> list) {
		Random random = new Random();
		
		long before = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			list.add(random.nextInt(10000));
		}
		long after = System.currentTimeMillis();
		
		System.out.println(after - before);
	}

	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new LinkedList<Integer>();
		
		getTime(list1);
		getTime(list2);
	}

}
