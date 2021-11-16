package dgsw.file;

import java.io.File;

public class FileStudy {

	public File makeFile(String directroy, String fileName) throws Exception {
		File file = new File(directroy, fileName);
		boolean result = file.createNewFile(); // 파일 생성

		System.out.println("파일 생성 : " + result);
		return file;
	}

	public File deleteFile(File file) throws Exception {
		boolean result = file.delete(); // 파일 삭제
		System.out.println("파일 삭제 : " + result);
		return null;
	}

	public File rename(File file, String newName) throws Exception {
		File newNameFile = new File(file.getParent(), newName);
		boolean result = file.renameTo(newNameFile);

		System.out.println("이름 변경 : " + result + " " + newNameFile.getAbsolutePath());

		return newNameFile;
	}

	public File move(File file, String directory) {
		File newLocation = new File(file.getParent() + "/" + directory, file.getName());
		boolean result = file.renameTo(newLocation);

		System.out.println("위치 이동 : " + result + " " + file.getAbsolutePath());
		return newLocation;
	}

	public static void main(String[] args) {
		try {
			FileStudy fileStudy = new FileStudy();
			File file = fileStudy.makeFile("C://dgsw_class", "first.abc");
			file = fileStudy.rename(file, "scond.qqq");

			System.out.println(file.getName());

			file = fileStudy.move(file, "sub1");
			
			file = fileStudy.deleteFile(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
