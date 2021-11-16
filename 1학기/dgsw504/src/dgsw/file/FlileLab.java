package dgsw.file;

import java.io.File;

public class FlileLab {

	public static void main(String[] args) throws Exception {
		File file = new File("C://dgsw_class", "sample.txt");
		System.out.println("getName : " + file.getName()); //이름 가져오기
		boolean exist = file.exists(); // 하드 디스크에 존재하는지
		System.out.println("exist : " + exist);

		boolean isDirectory = file.isDirectory(); //디렉토리인지 파일인지
		System.out.println("isDirectory : " + isDirectory);
		
		File dir = new File("C://dgsw_class");
		boolean isDirectory1 = dir.isDirectory();
		System.out.println("isDirectory1 : " + isDirectory1);
		
		String path = file.getAbsolutePath();
		System.out.println("getAbsolutePath : " + path);
		
		String path1 = file.getCanonicalPath();
		System.out.println("getCanonicalPath : " + path1);
		
		long length = file.length();
		System.out.println("length : " + length);
		
		length = dir.length();
		System.out.println("dir of length : " + length); //dir의 length는 0
	}
}
