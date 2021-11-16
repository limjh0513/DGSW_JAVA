package kr.hs.dgsw.javaClass.nio;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStudy {
    public static void studyPath() throws Exception{
        Path path = Paths.get("C:\\study\\nio\\hello.txt");
        //Path path = Paths.get("C:", "study", "nio", "hello.txt");
        System.out.println(String.format("파일 이름 : %s", path.getFileName()));
        System.out.println(String.format("부모 디렉토리 : %s", path.getParent().getFileName()));
        System.out.println(String.format("중첩 경로 수 : %s", path.getNameCount()));

        System.out.println(String.format("디렉토리 여부 : %s", Files.isDirectory(path)));
        System.out.println(String.format("파일 여부 : %s", Files.isRegularFile(path)));
        System.out.println(String.format("마지막 수정 시간 : %s", Files.getLastModifiedTime(path)));
        System.out.println(String.format("파일 크기 : %s", Files.size(path)));
        System.out.println(String.format("소유자 : %s", Files.getOwner(path)));
        System.out.println(String.format("숨김 여부 : %s", Files.isHidden(path)));
        System.out.println(String.format("읽기 가능 여부 : %s", Files.isReadable(path)));
        System.out.println(String.format("쓰기 가능 여부 : %s", Files.isWritable(path)));
    }

    public static void studyFileManagement() throws Exception{
        System.out.println("파일 디렉토리 생성 삭제");

        Path dirPath = Paths.get("C:\\study\\nio", "myDir");
        Path filePath = Paths.get("C:\\study\\nio", "myFile.txt");

        if(Files.notExists(dirPath)){
            Files.createDirectories(dirPath);
        }

        if(Files.notExists(filePath)){
            Files.createFile(filePath);
        }

        Path parentPath = Paths.get("C:\\study\\nio");
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(parentPath);

        for(Path path : directoryStream){
            if(Files.isDirectory(path)){
                System.out.println(String.format("디렉토리 : %s", path.getFileName()));
            } else {
                System.out.println(String.format("파일 : %s (%d)", path.getFileName(), Files.size(path)));
            }
        }
    }


    public static void main(String[] args) {
        try{
            studyPath();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}