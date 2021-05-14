package dgsw.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Writer {
	public static void main(String[] args) {
		try {
			File file = new File("C://dgsw_class", "fileA");
			file.createNewFile();
			
			OutputStream os = new FileOutputStream(file);
			
			String str = "나 보기가 역겨워 가실 때에는 말없이 고이 보내 드리오이다.";
			byte[] bytes = str.getBytes();
			
			os.write(bytes); //아래 for문보다 더 효율적이게
			
//			for(byte b : bytes) {
//				os.write(b);
//			}
			
//			char ch = 'A';
//			os.write(ch);
//			ch = 'B';
//			os.write(ch);
//			ch = 'S';
//			os.write(ch);
//			ch = 'D';
//			os.write(ch);
//			ch = 'W';
//			os.write(ch);
//			ch = 'A';
//			os.write(ch);
			
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
