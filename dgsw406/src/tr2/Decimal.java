package tr2;

import java.util.Scanner;

public class Decimal {
	int input;

	public Decimal(int input) {
		this.input = input;
	}

	public String result() {
		int check = 0;
		String str = "";
		
		for (int i = 2; i <= input; i++) {
			check = 0;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					check = 1;
					break;
				}	
			}
			
			if (check == 0) {
				if(str.equals("")) {
					str = str + i;
				}
				else {
					str = str + ", " + i;
				}
			}

		}

		return str;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		Decimal dec = new Decimal(input);
		
		System.out.println(dec.result());
	}
}
