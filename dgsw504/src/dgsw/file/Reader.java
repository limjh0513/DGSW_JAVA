package dgsw.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

public class Reader {
	public static void main(String[] args) {
		try {
			File file = new File("C://dgsw_class/fileA");
			InputStream is = new FileInputStream(file); //바이너리 파일이라면 inputStream을 사용
			BufferedReader reader = new BufferedReader(new FileReader(file)); //텍스트 파일이라면 bufferedReader 사용이 편함

			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			byte[] buffer = new byte[1024];
			byte[] bytes = new byte[0];
			int length = is.read(buffer);

			String str = new String(buffer, 0, length);
			System.out.println(str);

//			while(is.available() > 0) {
//				int value = is.read();
//				//is.read(buffer);
//				//System.out.println(new String(buffer,"UTF-8"));
//				System.out.println(value);
//			}

			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
