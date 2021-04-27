package study;

public class MyList {

	MyListNode head = null;
	MyListNode tail = null;

	int total = 0;

	public void add(String value) {
		MyListNode node = new MyListNode();
		node.setValue(value);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.setnext(node);
			tail = node;
		}
		total++;
	}

	public void delete(int index) {
		if (head != null) {
			MyListNode node = head;
			if (index == 0) {
				head = head.getnext();
				node.setnext(null);
				total--;
			} else {
				MyListNode step = null;
				for (int i = 0; i < total; i++) {
					if (i == index) {
						System.out.println("delete : " + node.getValue());
						step.setnext(node.getnext());
						node.setnext(null);
						total--;
						break;
					}
					step = node;
					node = node.getnext();
				}
			}
		}
	}

	public int getSize() {
		return total;
	}

	public String get(int index) {
		if (head != null) {
			MyListNode step = head;
			for (int i = 0; i < total; i++) {
				if (index == i) {
					return step.getValue();
				}
				step = step.getnext();
			}
		}

		return null;
	}

	public static void main(String[] args) {
		MyList list = new MyList();

		list.add("대구소프트웨어마이스터고등학교");
		list.add("JAVA 수업");
		list.add("2학년 1반");
		System.out.println(list.getSize());

		System.out.println(list.get(2));
		System.out.println(list.get(1));
		System.out.println(list.get(0));

		list.delete(2);

		System.out.println(list.getSize());
		
		System.out.println(list.get(0));
		System.out.println(list.get(1));
	}
}
