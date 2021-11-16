package tryCatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TryStudy {

	public static void method1() {
		String str = null;
		try {
			System.out.println(str.length());

			//int a = 5 / 0;
			
			//IoException은 예외처리를 하지 않으면 에러가 남
			//RuntimeException은 예외처리를 하지 않아도 오류가 나지는 않는다(별도의 예외처리가 필요 없음)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void method2() {
		BufferedReader reader = null;
		try {
			File file = new File("C://abc.txt");
			FileReader fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			System.out.println(line);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// return 하기 전 finally를 실행해준다
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void method3() throws Exception {
		File file = new File("C://abc.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = reader.readLine();
		System.out.println(line);
		reader.close();
	}

	public static void main(String[] args) {
		// method1();
		// method2();
		try {
			method3();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
