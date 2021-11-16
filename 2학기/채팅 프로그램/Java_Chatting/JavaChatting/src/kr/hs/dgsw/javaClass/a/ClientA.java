package kr.hs.dgsw.javaClass.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientA extends Thread {
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private Scanner sc;

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int PORT = 1200;

    final static int BYTE_LENGTH = 5010;

    private String id;

    public void startClient() throws Exception {
        connect();
        prepareTalking();
        startChatting();

        ReadMessage rm = new ReadMessage(socket);
        WriteMessage wm = new WriteMessage(socket);

        rm.start();
        wm.start();
        rm.join();
        wm.join();

        disconnect();
    }

    public void connect() throws Exception {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            prepareTalking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareTalking() throws Exception {
        is = socket.getInputStream();
        os = socket.getOutputStream();
        sc = new Scanner(System.in);
    }

    private void startChatting() throws IOException {
        System.out.print("아이디 4자리와 이름 입력 : ");
        String idInput = sc.nextLine();
        id = splitStr(idInput)[0];
        idInput = "ID " + idInput;
        os.write(idInput.getBytes(StandardCharsets.UTF_8));
        os.flush();
    }

    public String[] splitStr(String message) {
        String[] str = message.split(" ");
        return str;
    }

    public void disconnect() throws Exception {
        if (socket != null) {
            socket.close();
        }

        if (is != null) {
            is.close();
        }

        if (os != null) {
            os.close();
        }

        if (sc != null) {
            sc.close();
        }

    }

    class ReadMessage extends Thread {
        byte[] bytes;
        private Socket socket;

        public ReadMessage(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    bytes = new byte[1024];
                    int length = is.read(bytes);
                    String read = new String(bytes, 0, length);
                    System.out.println(read);

                    if (read.equals("중복된 아이디가 있어요")) {
                        System.out.println("접속이 종료됩니다...");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        disconnect();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    class WriteMessage extends Thread {
        private Socket socket;

        public WriteMessage(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.print("명령어와 메시지 입력 : ");
                    String message = sc.nextLine();
                    String[] str = splitStr(message);
                    String send = null;

                    if(str.length == 2){
                        send = str[0] + " " + id + " " + str[1];
                    } else if(str.length == 3) {
                        send = str[0] + " " + id + " " + str[1] +" " + str[2];
                    }

                    os.write(send.getBytes(StandardCharsets.UTF_8));
                    os.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ClientA client = new ClientA();
            client.startClient();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
