package sumProject;

public class Sumclass {
	public static void main(String[] args) {
		int n1 = 5;
		int n2 = 12;

		int result = sums(n1, n2);
		System.out.println(n1 + " + " + n2 + " = " + result);
	}

	public static int sums(int n1, int n2) {
		return n1 + n2;
	}
}
