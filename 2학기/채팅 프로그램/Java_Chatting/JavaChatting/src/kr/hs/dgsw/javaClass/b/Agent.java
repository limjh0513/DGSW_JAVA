package kr.hs.dgsw.javaClass.b;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Agent {
    private Socket socket = null;
    private InputStream is;
    private OutputStream os;

    private String id = "";
    private String name = "";
    private Boolean master = false;

    public Agent(Socket socket) throws Exception {
        this.socket = socket;
        prepareTalking();
    }

    public class AgentThread extends Thread {
        private Agent agent;

        public AgentThread(Agent agent) {
            this.agent = agent;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    byte[] getMessage = new byte[5000];
                    int length = is.read(getMessage);
                    String read = new String(getMessage, 0, length);

                    String header;
                    String commendLength;
                    String payload;

                    if(read.length() > 6){
                        header = read.substring(0, 2);
                        commendLength = read.substring(2, 4);
                        payload = read.substring(6);
                    } else {
                        header = read.substring(0, 2);
                        commendLength = read.substring(2, 4);
                        payload = "";
                    }

                    System.out.println(header + commendLength + payload);

                    switch (header) {
                        case "ID": {
                            String inputId = payload.substring(0,4);
                            String inputName = payload.substring(4);

                            Boolean result = commendDR(inputId);
                            //System.out.println(result);

                            if (result) {
                                Boolean access = checkClientAccess();

                                if (access) {
                                    master = true;
                                    sendMessage("HD"); //방장 권한
                                }

                                id = inputId;
                                name = inputName;

                                messageUR();
                            } else {
                                sendMessage("DR");
                                disconnected();
                            }
                            break;
                        }
                        case "GM": {
                            String messageGM = payload;

                            for (Agent a : Server.clients) { //SR
                                /* if (a.id.equals(id)) {
                                    continue;
                                } */

                                sendMessage("GR", id + messageGM, a.os);
                            }
                            break;
                        }
                        case "WD": {
                            Agent target = null;

                            for (Agent a : Server.clients) {
                                if (a.id.equals(payload)) {
                                    target = a;
                                }
                            }

                            if (master && target != null) {
                                sendMessage("WR", target.os); //추방 메시지
                                target.disconnected();
                                Server.clients.remove(target);

                                sendMessage("SD"); //추방 성공
                                messageWA(payload);

                            } else if (!master) {
                                sendMessage("NA"); //권한 없음 전달
                            } else if (target == null) {
                                sendMessage("NC"); //삭제 대상 없음 전달
                            }
                            break;
                        }
                        case "SM": {
                            String targetId = payload.substring(0,4);
                            String messageSM = payload.substring(4);
                            Boolean sendResult = false;

                            for (Agent a : Server.clients) {
                                if (a.id.equals(targetId)) {
                                    sendMessage("SR", id + messageSM, a.os);
                                    sendResult = true;
                                }
                            }

                            if (sendResult) {
                                sendMessage("SS"); //귓속말 전달 성공
                            } else {
                                sendMessage("SF"); //귓속말 전달 실패
                            }

                            break;
                        }
                        default: {
                            sendMessage("ER"); //명령어 오류 전달
                            break;
                        }
                    }

                } catch (Exception e) {
                    System.out.println(id + " 접속이 중단 됨");
                    try {
                        Server.clients.remove(agent);
                        messageDC(id);
                        disconnected();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    private void messageDC(String id) throws IOException {
        for (Agent a : Server.clients) {
            sendMessage("DC", id, a.os); //추방 메시지 전달
        }
    }

    private void messageWA(String s) throws IOException {
        for (Agent a : Server.clients) {
            sendMessage("WA", s, a.os); //추방 메시지 전달
        }
    }

    private void messageUR() throws IOException {
        //System.out.println("유저 목록 전달");

        String list = "";
        for (Agent a : Server.clients) {
            if (a.id.equals("")) {
                continue;
            }
            list += a.id + " " + a.name + ",";
        }

        sendMessage("UR", list);

        messageJR();
    }

    private void messageJR() throws IOException { //JR
        System.out.println("새로운 유저가 들어왔어요! - " + id + "_"+ name);

        for (Agent a : Server.clients) {
            //System.out.println(a.id.equals(this.id));
            if (a.id.equals(this.id)) {
                continue;
            } else if (a.id.equals("")) {
                continue;
            }
            sendMessage("JR", id, a.os);
        }
    }

    public Boolean checkClientAccess() {
        Boolean result = true;

        for (Agent a : Server.clients) {
            if (a.id.equals(this.id)) {
                continue;
            } else if (!a.id.equals("")) {
                result = false;
                break;
            }
        }
        //System.out.println(result);
        return result;
    }


    private Boolean commendDR(String id) {
        Boolean result = true;

        for (Agent a : Server.clients) {
            if (a.id.equals(id)) { //DR
                result = false;
            }
        }

        return result;
    }

    public String checklength(String payload) {
        String result = payload.length() + "";
        int a = payload.length();
        int len = 0;

        if(a == 0){
            len = 1;
        } else {
            while(a > 0){
                a /= 10;
                len++;
            }
        }

        for (int i = len; i < 4; i++) {
            result = "0" + result;
        }
        return result;
    }

    public void prepareTalking() throws Exception {
        is = socket.getInputStream();
        os = socket.getOutputStream();

        AgentThread at = new AgentThread(this);
        at.start();
    }

    public void sendMessage(String header, String payload) throws IOException {
        String message = header + checklength(payload) + payload;
        //System.out.println("명령어 : " + message);
        os.write(message.getBytes(StandardCharsets.UTF_8));
        os.flush();
    }

    public void sendMessage(String header, OutputStream os) throws IOException {
        String message = header + checklength("");
        //System.out.println("명령어 : " + message);
        os.write(message.getBytes(StandardCharsets.UTF_8));
        os.flush();
    }

    public void sendMessage(String header, String payload, OutputStream os) throws IOException {
        String message = header + checklength(payload) + payload;
        //System.out.println("명령어 : " + message);
        os.write(message.getBytes(StandardCharsets.UTF_8));
        os.flush();
    }

    public void sendMessage(String header) throws IOException {
        String message = header + checklength("");
        //System.out.println("명령어 : " + message);
        os.write(message.getBytes(StandardCharsets.UTF_8));
        os.flush();
    }

    public void disconnected() {
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
