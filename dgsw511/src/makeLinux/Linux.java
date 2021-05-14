package makeLinux;

import java.io.File;
import java.util.Scanner;

public class Linux {

	public void lsCommand(String pos) {
		File file = new File(pos);
		File[] files = file.listFiles();

		for (File f : files) {
			System.out.println(f);
		}
	}

	public void touchCommand(String pos, String name) throws Exception {
		File mkName = new File(pos, name);
		boolean result = mkName.createNewFile();

		System.out.println("파일 생성 결과 : " + result);
	}

	public void rmCommand(String pos, String name) {
		File file = new File(pos, name);
		boolean exist = file.exists();

		if (exist) {
			boolean result = file.delete();
			System.out.println("파일 삭제 결과 : " + result);
		} else {
			System.out.println("찾고자 하는 파일이 없습니다...");
		}
	}

	public void mkdirCommand(String pos, String name) {
		File file = new File(pos + name);
		boolean result = file.mkdirs();
		System.out.println("폴더 생성 결과 : " + result);
	}

	public void rmdirCommand(String pos, String name) {
		File folder = new File(pos + name);
		while (folder.exists()) {
			File[] folder_list = folder.listFiles(); // 파일리스트 얻어오기

			for (int j = 0; j < folder_list.length; j++) {
				folder_list[j].delete(); // 파일 삭제
				System.out.println("파일이 삭제되었습니다.\n");
			}

			if (folder_list.length == 0 && folder.isDirectory()) {
				folder.delete(); // 대상폴더 삭제
				System.out.println("폴더가 삭제되었습니다.");
			}
		}
	}

	public boolean cdCommand(String pos) {
		File file = new File(pos);
		boolean result = file.exists();

		return result;
	}

	// ls, touch, rm, mkdir, rmdir, cd

	public static void main(String[] args) throws Exception {
		Linux linux = new Linux();
		int cmd = 1;
		String position = "C:\\";
		Scanner sc = new Scanner(System.in);

		while (cmd == 1) {
			System.out.print(position + " > ");
			String input = sc.next();
			switch (input) {
			case "ls": // 목록
				linux.lsCommand(position);
				break;
			case "touch": // 파일 생성
				String newFile = sc.next();
				linux.touchCommand(position, newFile);
				break;
			case "rm": // 지우기
				String delName = sc.next();
				linux.rmCommand(position, delName);
				break;
			case "mkdir": // 폴더 생성
				String newDir = sc.next();
				linux.mkdirCommand(position, newDir);
				break;
			case "rmdir": // 폴더 지우기
				String rmdirName = sc.next();
				linux.rmdirCommand(position, rmdirName);
				break;
			case "cd": // 이동
				String cdPos = sc.next();
				boolean cdCheck;
				if (cdPos.equals("..")) {
					String[] list = position.split("\\\\");
					position = list[0] + "\\";
					for (int i = 1; i < list.length - 1; i++) {
						position += list[i] + "\\";
					}
				} else {
					cdCheck = linux.cdCommand(position + cdPos + "\\");
					if (cdCheck) {
						position += cdPos + "\\";
					} else {
						System.out.println("이동하려는 위치가 존재하지 않습니다.");
					}

				}
				break;

			default:
				System.out.println("다른 값 입력...\n정말 종료하시겠습니까 ?(yes 시 종료)");
				String isYes = sc.next();
				if (isYes.equals("yes")) {
					cmd = 0;
				}

				break;
			}

		}

		System.out.println("종료");
		sc.close();
	}
}
