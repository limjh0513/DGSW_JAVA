package kr.hs.dgsw.java.c1.tr1.poly.Array;

public class Stack {
	private static final int MAX = 100;
	private String[] stack = new String[MAX];
	int top = -1;

	public void push(String str) { // 스택 값 삽입
		if (top < MAX) {
			stack[++top] = str;
			System.out.println("삽입 완료...\n" + "삽입하려는 글 : " + str + "\n들어간 글 : " + stack[top] + " " + top);

		} else {
			System.out.println("범위를 초과했습니다.");
		}
	}

	public String pop() { // 스택 값 빼기
		if (top >= 0) {
			System.out.println("값을 뺐습니다 : " + stack[top]);
			String sendStr = stack[top];
			top--;

			return sendStr;
		} else {
			System.out.println("값이 존재하지 않습니다.");
			return null;
		}
	}

	public int returnMaxSize() { // stack 최대 크기
		return stack.length;
	}

	public int returnSize() { // stack 현재 크기
		return top;
	}

	public String getStackIndex(int idx) { // 원하는 index 값 보여주기
		if (top >= idx) {
			return stack[idx];
		} else {
			System.out.println("값이 존재하지 않습니다.");
			return null;
		}
	}

	public void stackPrint() {
		if (top >= 0) {
			for (int i = 0; i <= top; i++) {
				System.out.println(i + "번째 : " + stack[top]);
			}
		} else {
			System.out.println("값이 존재하지 않습니다.");
		}
	}

	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push("가");
		stack.push("나");
		stack.push("다");

		System.out.println(stack.returnSize());
		System.out.println(stack.getStackIndex(2));
		
		
		stack.stackPrint();

		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
	}

}
