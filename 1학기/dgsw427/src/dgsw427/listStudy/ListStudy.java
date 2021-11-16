package dgsw427.listStudy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListStudy {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		List<Integer> list1 = new LinkedList<Integer>();
		ArrayList<String> list2 = new ArrayList<String>(); //이렇게 사용하는 것 보다 9,10번 줄 처럼 선언해서 사용하는 것이 좋음
		
		list.add("대구소프트웨어고등학교");
		list.add("2학년 1반");
		list.add(3+"");
		list.add(0, "대한민국");
		
		String str1 = list.get(1);
		Boolean con = list.contains("대구소프트웨어"); //false
		String str = list.get(15); //indexout...Exception
		
		
		list1.add(5);
		list1.add(8);
		list1.add(4);
	}
}
