package kr.hs.dgsw.javaClass.b;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private Scanner sc;

    //private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final String SERVER_ADDRESS = "10.80.163.146";
    private static final int PORT = 1200;

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

    private void startChatting() throws IOException {
        System.out.print("아이디 4자리와 이름 입력 : ");
        String idInput = sc.nextLine();
        id = idInput.substring(0, 2);
        sendMessage("ID", idInput);
    }

    public void sendMessage(String header, String payload) throws IOException {
        String message = header + checklength(payload) + payload;
        os.write(message.getBytes(StandardCharsets.UTF_8));
        os.flush();
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
                    bytes = new byte[5000];
                    int length = is.read(bytes);
                    String read = new String(bytes, 0, length);

                    //System.out.println("전달값: " + read);
                    Boolean connectCancel = false;

                    String header;
                    String commendLength;
                    String payload;

                    if (read.length() > 6) {
                        header = read.substring(0, 2);
                        commendLength = read.substring(2, 4);
                        payload = read.substring(6);
                    } else {
                        header = read.substring(0, 2);
                        commendLength = read.substring(2, 4);
                        payload = "";
                    }

                    //System.out.println("명령어 : " + header + " " + commendLength + " " + payload);

                    switch (header) {
                        case "HD": {
                            System.out.println("지금부터 방장이에요");
                            break;
                        }
                        case "DR": {
                            System.out.println("중복된 아이디가 있어요...");
                            System.out.println("접속이 종료됩니다.");
                            connectCancel = true;
                            break;
                        }
                        case "GR": {
                            String readId = payload.substring(0, 4);
                            String readM = payload.substring(4);
                            String str = "전체 메시지 : " + readId + " - " + readM;
                            System.out.println(str);
                            break;
                        }
                        case "WR": {
                            System.out.println("관리자로부터 추방당했습니다...");
                            connectCancel = true;
                            break;
                        }
                        case "SD": {
                            System.out.println("추방에 성공했습니다.");
                            break;
                        }
                        case "NA": {
                            System.out.println("추방할 권한이 존재하지 않습니다.");
                            break;
                        }
                        case "NC": {
                            System.out.println("삭제할 아이디가 조재하지 않습니다.");
                            break;
                        }
                        case "SR": {
                            String readId = payload.substring(0, 4);
                            String readM = payload.substring(4);
                            String str = "귓속말 : " + readId + " - " + readM;
                            System.out.println(str);
                            break;
                        }
                        case "SS": {
                            System.out.println("귓속말 전송 성공");
                            break;
                        }
                        case "SF": {
                            System.out.println("귓속말 전송 실패");
                            break;
                        }
                        case "ER": {
                            System.out.println("잘못된 명령어입니다.");
                            break;
                        }
                        case "WA": {
                            String str = payload + "가 관리자로부터 추방당했습니다.";
                            //System.out.println(str);
                            break;
                        }
                        case "UR": {
                            String str = "유저 목록 : " + payload;
                            System.out.println(str);
                            break;
                        }
                        case "JR": {
                            String str = "새로운 유저가 들어왔어요 : " + payload;
                            System.out.println(str);
                            break;
                        }

                        case "DC": {
                            String str = payload + " 유저가 나갔어요";
                            System.out.println(str);
                            break;
                        }
                    }

                    if (connectCancel) {
                        disconnect();
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        disconnect();
                        interrupt();
                    } catch (Exception exception) {
                    }
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
                    sendMessage(message);

                    if (socket.isClosed()) {
                        disconnect();
                        interrupt();
                        break;
                    }
                } catch (Exception e) {
                    try {
                        disconnect();
                        interrupt();
                    } catch (Exception exception) {
                    }
                }
            }
        }
    }

    public void sendMessage(String inputs) throws IOException {
        String header = inputs.substring(0, 2);
        String payload = inputs.substring(2);

        String message = header + checklength(payload) + payload;
        os.write(message.getBytes(StandardCharsets.UTF_8));
        os.flush();
    }

    public String checklength(String payload) {
        String result = payload.length() + "";
        int a = payload.length();
        int len = 0;

        if (a == 0) {
            len = 1;
        } else {
            while (a > 0) {
                a /= 10;
                len++;
            }
        }
        for (int i = len; i < 4; i++) {
            result = "0" + result;
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.startClient();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
