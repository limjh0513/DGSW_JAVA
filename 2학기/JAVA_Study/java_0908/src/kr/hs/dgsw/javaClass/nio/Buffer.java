package kr.hs.dgsw.javaClass.nio;

import java.nio.ByteBuffer;

public class Buffer {
    public static void main(String[] args) {
        try {
            studyBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void studyBuffer() throws Exception {
        ByteBuffer buffer1 = ByteBuffer.allocate(10);

        byte[] bytes = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer buffer2 = ByteBuffer.wrap(bytes);

        printStatus(buffer1, "Buffer1 생성");
        printStatus(buffer2, "Buffer2 생성");

        enter();

        //buffer1 값 기록
        buffer1.put((byte) 55);
        buffer1.put((byte) -12);
        printStatus(buffer1, "Buffer1에 2byte 추가");

        enter();

        buffer1.put(new byte[]{15, 16, 17, 18});
        printStatus(buffer1, "Buffer1에 4byte 추가");

        enter();

        byte value = buffer2.get();
        System.out.println("읽은 값 : " + value);
        printStatus(buffer2, "buffer2에서 1byte 읽음");
        byte[] bytes1 = new byte[3];
        buffer2.get(bytes1);

        enter();

        printStatus(buffer2, "buffer2에서 3byte 읽음");

        printStatus(buffer1, "buffer1 flip() 수행 전");
        //처음으로 돌아가기
        buffer1.flip(); //position 값이 limit이 되고 position은 0이 됨
        printStatus(buffer1, "buffer1 flip() 수행 후");

        enter();

        value = buffer1.get();
        System.out.println(value);
        printStatus(buffer1, "buffer1에서 1byte 읽음");

        enter();

        printStatus(buffer1, "buffer1 rewind() 수행 전");
        buffer1.rewind(); //0으로 돌아감
        printStatus(buffer1, "buffer1 rewind() 수행 후");

/*        printStatus(buffer1, "buffer1 clear() 수행 전");
     buffer1.clear(); //버퍼를 완전 처음으로, 넣어 놓은 데이터는 남아있음
        printStatus(buffer1, "buffer1 clear() 수행 후");
*/

        enter();

        buffer1.get(bytes1);
        printStatus(buffer1, "buffer1 상태");
        buffer1.mark();
        buffer1.get();
        printStatus(buffer1, "buffer1 reset 수행 전");
        buffer1.reset();
        printStatus(buffer1, "buffer1 reset 수행 후");

        enter();

        printStatus(buffer1, "buffer1 compact 수행 후");
        buffer1.compact(); //position 뒤에 있는 부분을 삭제하고, position부터 뒤에 있는 값은 0, 1, 2로 이동
        printStatus(buffer1, "buffer1 compact 수행 후");

        enter();

        buffer1.rewind();
        printStatus(buffer1, "buffer1 rewind 수행 후");
        value = buffer1.get();
        System.out.println(value);
    }

    public static void printStatus(ByteBuffer buffer, String note) {
        System.out.println(String.format("%s : %d %d %d", note, buffer.position(), buffer.limit(), buffer.capacity()));
    }

    public static void enter(){
        System.out.println();
    }
}
