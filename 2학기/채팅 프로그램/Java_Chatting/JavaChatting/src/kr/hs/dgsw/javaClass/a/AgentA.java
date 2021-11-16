package kr.hs.dgsw.javaClass.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class AgentA {
    private Socket socket = null;
    private InputStream is;
    private OutputStream os;

    private String id = "";
    private String name = "";
    private Boolean master = false;

    public class MessageListen extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    byte[] getMessage = new byte[5000];
                    int length = is.read(getMessage);
                    String[] message = new String(getMessage, 0, length).split(" ");
                    System.out.println(new String(getMessage, 0, length) + " " + message.length);

                    if (message.length != 3 && message.length != 4) {
                        os.write("잘못된 형식의 메시지".getBytes(StandardCharsets.UTF_8));
                        os.flush();
                    } else if (message.length == 3) {
                        if (message[0].equals("ID")) { //ID
                            for (AgentA a : ServerA.clients) {
                                if (a.id.equals(message[1])) { //DR
                                    os.write("중복된 아이디가 있어요".getBytes(StandardCharsets.UTF_8));
                                    os.flush();
                                    close();
                                }
                            }

                            Boolean result = checkClientAccess();

                            if(result){
                                master = true;
                                os.write("지금부터는 방장이에요!".getBytes(StandardCharsets.UTF_8));
                                os.flush();
                            }

                            id = message[1];
                            name = message[2];

                            showClientList();

                        } else if (message[0].equals("GM")) {
                            String id = message[1];
                            String payload = message[2];

                            String sendMs = "전체 메시지 : " + id + " " + payload;

                            System.out.println("전달할 값 : " + sendMs);
                            for (AgentA a : ServerA.clients) { //SR
                                if(a.id.equals(id)){
                                    continue;
                                }
                                System.out.println(a.id + "에게 전송 : " + sendMs);
                                a.os.write(sendMs.getBytes(StandardCharsets.UTF_8));
                                a.os.flush();
                            }

                        } else if (message[0].equals("WD")) { //WD
                            Boolean access = false;
                            AgentA target = null;

                            for (AgentA a : ServerA.clients) {
                                if (a.id.equals(message[1]) && a.master) {
                                    access = true;
                                }

                                if (a.id.equals(message[2])) {
                                    target = a;
                                }
                            }

                            if (access && target != null) { //WA
                                target.os.write("방장이 추방하였습니다.".getBytes(StandardCharsets.UTF_8));
                                target.os.flush();
                                target.close();
                                ServerA.clients.remove(target);

                                os.write("추방에 성공하였습니다.".getBytes(StandardCharsets.UTF_8));
                                os.flush();

                                expelClient(message[2]);
                            } else if (!access) {
                                os.write("권한이 존재하지 않습니다.".getBytes(StandardCharsets.UTF_8));
                                os.flush();
                            } else {
                                os.write("존재하지 않는 ID 입니다.".getBytes(StandardCharsets.UTF_8));
                                os.flush();
                            }

                        } else {
                            os.write("존재하지 않는 명령어입니다.".getBytes(StandardCharsets.UTF_8));
                            os.flush();
                        }
                    } else if (message.length == 4) {
                        String target = message[2];
                        Boolean result = false;

                        if (message[0].equals("SM")) { //SM
                            for (AgentA a : ServerA.clients) {
                                if (a.id.equals(target)) { //SR
                                    String secretMessage = "귓속말 : " + message[1] + " " + message[3];
                                    a.os.write(secretMessage.getBytes(StandardCharsets.UTF_8));
                                    a.os.flush();
                                    result = true;
                                }
                            }

                            if (result) {
                                os.write("귓속말 전송이 성공했습니다.".getBytes(StandardCharsets.UTF_8));
                                os.flush();
                            } else {
                                os.write("귓속말 전송에 실패했습니다.".getBytes(StandardCharsets.UTF_8));
                                os.flush();
                            }
                        } else {
                            os.write("존재하지 않는 명령어입니다.".getBytes(StandardCharsets.UTF_8));
                            os.flush();
                        }
                    }


                } catch (IOException e) {
                    System.out.println(id + " : 접속 종료!!!");
                    ServerA.clients.remove(this);
                    clientToOutServer();
                    close();
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public Boolean checkClientAccess() throws IOException {
        Boolean result = true;

        for(AgentA a : ServerA.clients){
            if(a.id.equals(this.id)){
                continue;
            }
            else if(!a.id.equals("")){
                result = false;
                break;
            }
        }
        return result;
    }


    public void clientToOutServer() {
        try {
            for (AgentA a : ServerA.clients) {
                String msg = id + "가 접속을 종료했습니다...";
                a.os.write(msg.getBytes(StandardCharsets.UTF_8));
                a.os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void expelClient(String id) throws IOException {
        String message = id + "가 추방당하였습니다.";
        for (AgentA a : ServerA.clients) {
            a.os.write(message.getBytes(StandardCharsets.UTF_8));
            a.os.flush();
        }
    }

    public void showClientList() throws IOException { //UR
        System.out.println("유저 목록 전달");

        String list = "유저 목록 : ";
        for (AgentA a : ServerA.clients) {
            if(a.id.equals("")){
                continue;
            }
            list += a.id + " " + a.name + ",";
        }
        os.write(list.getBytes(StandardCharsets.UTF_8));
        os.flush();

        alertUser();
    }

    private void alertUser() throws IOException { //JR
        System.out.println("새로운 유저가 들어왔어요!");

        for (AgentA a : ServerA.clients) {
            if(a.id.equals(id)){
                continue;
            } else if(a.id.equals("")){
                continue;
            }

            String msg = "새로운 유저가 들어왔어요 : " + id;
            a.os.write(msg.getBytes(StandardCharsets.UTF_8));
            a.os.flush();
        }
    }

    public AgentA(Socket socket) throws Exception {
        this.socket = socket;
        prepareTalking();
    }

    public void prepareTalking() throws Exception {
        is = socket.getInputStream();
        os = socket.getOutputStream();

        MessageListen ms = new MessageListen();
        ms.start();
    }

    public void close() {
        try {
            if (is != null) {
                is.close();
            }

            if (os != null) {
                os.close();
            }

            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
