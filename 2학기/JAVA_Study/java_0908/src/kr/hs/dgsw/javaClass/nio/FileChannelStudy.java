package kr.hs.dgsw.javaClass.nio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelStudy {
    public static void main(String[] args) {
        try {
            studyWrite();
            studyRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void studyWrite() throws Exception {
        Path path = Paths.get("C:\\study\\nio\\abc.txt");

        FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        String data = "대구 소프트웨어 마이스터 고등학교";

        byte[] bytes = data.getBytes("UTF-8");
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        channel.write(buffer);

        channel.close();
    }

    public static void studyRead() throws Exception {
        Path path = Paths.get("C:\\study\\nio\\abc.txt");
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        byte[] bytes = new byte[100];
        String data = "";

        while (true) {
            int count = channel.read(buffer);
            if (count < 0) {
                break;
            }
            buffer.flip();
            buffer.get(bytes, 0, count);
            data += new String(bytes, 0, count, "UTF-8");

            buffer.clear();
        }
        channel.close();
        System.out.println(data);
    }

    public static void studyWrite1() throws Exception {

        Path path = Paths.get("C:\\study\\nio\\dgsw.txt");
        FileChannel channel =
                FileChannel.open(path,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE);

        String data = "대구소프트웨어고등학교";
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer buffer = charset.encode(data);

        int count = channel.write(buffer);
        System.out.println(
                String.format("%s 파일에 %d bytes을 기록하였습니다.",
                        path.getFileName(), count));

        channel.close();
    }

    public static void studyRead1() throws Exception {

        Path path = Paths.get("C:\\study\\nio\\dgsw.txt");
        FileChannel channel =
                FileChannel.open(path,
                        StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(10);
        Charset charset = Charset.forName("UTF-8");

        int count;
        String data = "";

        while (true) {
            count = channel.read(buffer);

            if (count < 0) {
                break;
            }

            buffer.flip();

            data += charset.decode(buffer).toString();
            buffer.clear();
        }

        channel.close();

        System.out.println("읽은 문자열 : " + data);

    }
}
