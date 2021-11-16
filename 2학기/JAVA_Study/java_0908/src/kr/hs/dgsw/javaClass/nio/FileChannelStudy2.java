package kr.hs.dgsw.javaClass.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;

public class FileChannelStudy2 {
    public static void main(String[] args) {
        try {
            studyWrite();
            studyRead();
            copy();
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
        FileChannel channel =
                FileChannel.open(path,
                        StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(5);
        byte[] bytes = new byte[5];
        String data = "";
        byte[] all = new byte[0];

        while (true) {
            int count = channel.read(buffer);

            if (count < 0) {
                break;
            }

            buffer.flip();
            buffer.get(bytes, 0, count);

            all = concatArrays(all, Arrays.copyOf(bytes, count));

            buffer.clear();
        }

        data = new String(all, "UTF-8");
        System.out.println(data);

        channel.close();
        System.out.println("data : " + data);
    }

    public static byte[] concatArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static void copy() throws Exception {
        Path src = Paths.get("C:\\study\\nio\\city.jfif");
        Path target = Paths.get("C:\\study\\nio\\city_copy.jfif");

        FileChannel channel = FileChannel.open(src, StandardOpenOption.READ);
        FileChannel channel2 = FileChannel.open(target, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocateDirect(100);

        int byteCount;

        while (true) {
            buffer.clear();
            byteCount = channel.read(buffer);

            if (byteCount < 0) {
                break;
            }

            buffer.flip();
            channel2.write(buffer);
        }

        channel.close();
        channel2.close();
        System.out.println("[[파일 복사 성공]]");
    }

    public static void copy2() throws Exception {
        Path src = Paths.get("C:\\study\\nio\\city.jfif");
        Path target = Paths.get("C:\\study\\nio\\city_copy.jfif");

        Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);
    }

}
