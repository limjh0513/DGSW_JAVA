package linkedList;

public class MyListNode {
	private String value;
	private MyListNode next;
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setNext(MyListNode next) {
		this.next = next;
	}
	
	public MyListNode getNext() {
		return next;
	}
	
}
