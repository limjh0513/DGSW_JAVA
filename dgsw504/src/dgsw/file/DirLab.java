package dgsw.file;

import java.io.File;

public class DirLab {
	public static void main(String[] args) throws Exception {

		File dir = new File("C://dgsw_class//sub3", "qqqq");

		boolean exist = dir.exists();
		System.out.println("exist : " + exist);

		boolean result = dir.mkdirs(); // 존재하지 않는 폴더에 생성할 수 있음 mkdir은 불가

		System.out.println("mkdirs : " + result);

		dir.delete();

		File dir1 = new File("C://dgsw_class");
		File[] files = dir1.listFiles();
		for (File file : files) {
			if(file.isDirectory()) {
				System.out.println("D : " + file.getName());
			} else {
				System.out.println("F : " + file.getName() + " " + file.length());
			}
		}
	}
}
