package linkedList;

public class MyList {

	private MyListNode head = null;
	private MyListNode tail = null;
	private int nodeCount = 0;

	public void add(String value) { // 노드 추가

		MyListNode node = new MyListNode();

		if (head == null) {
			node.setValue(value);
			head = node;
			tail = node;
		} else {
			tail.setNext(node);
			node.setValue(value);
			tail = node;
		}
		nodeCount++;
		System.out.println("성공적으로 추가되었습니다. : " + node.getValue() + "\n인덱스 : " + (nodeCount - 1));
	}

	public void delete(int index) { // 노드 삭제
		if (head != null) {
			MyListNode node = head;
			MyListNode step = null;
			for (int i = 0; i < nodeCount; i++) {

				if (i == index) {
					if(index == 0) {
						head = node.getNext();
						node.setNext(null);
					} else {
						step.setNext(node.getNext());
						node.setNext(null);
					}
					System.out.println("성공적으로 삭제 되었습니다. : " + node.getValue());
					nodeCount--;
					break;
				}
				step = node;
				node = node.getNext();
			}
		} else {
			System.out.println("값이 비어있습니다.");
		}
	}

	public String get(int index) { // 값 읽어오기
		if (head == null) {
			return "값이 비어있습니다.";
		} else {
			MyListNode node = head;
			for (int i = 0; i < nodeCount; i++) {
				if (i == index) {
					return node.getValue();
				}
				node = node.getNext();
			}
		}
		return null;

	}

	public int getSize() {
		return nodeCount;
	}

	public void printList() {
		MyListNode node = head;
		for (int i = 0; i < nodeCount; i++) {
			System.out.println(node.getValue());
			node = node.getNext();
		}
	}

	public static void main(String[] args) {
		MyList list = new MyList();

		list.add("대구소프트웨어마이스터고등학교");
		list.add("JAVA 수업");
		list.add("2학년 1반");

		System.out.println(list.get(2));

		list.printList();

		list.delete(0);

		System.out.println(list.getSize());

		list.printList();

	}
}
