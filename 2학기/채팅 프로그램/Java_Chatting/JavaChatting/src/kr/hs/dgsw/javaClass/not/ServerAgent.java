package kr.hs.dgsw.javaClass.not;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ServerAgent extends Thread {

    static ArrayList<ServerAgent> agents = new ArrayList<>();

    final static int BYTE_LENGTH = 5010;

    private Socket socket = null;
    private InputStream is;
    private OutputStream os;

    private String id = "";
    private String name = "";
    private Boolean master = false;

    byte[] bytes;

    public ServerAgent(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            prepareTalking();
            Id();
            Ur();
        } catch (Exception e) {
            System.out.println("서버가 종료되었습니다.");
            try {
                Dc();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            close();
            e.printStackTrace();
        }
    }

    private void runningServer() throws IOException {
        while (true) {
            bytes = new byte[BYTE_LENGTH];
            int length = is.read(bytes);
            String[] getMessage = null;


            if (length > 0) {
                getMessage = new String(bytes, 0, length).split(" ");
            }
            String mode = null;
            String id = null;
            String message = null;

            if (getMessage != null && getMessage.length >= 3) {
                mode = getMessage[0];
                id = getMessage[1];
                message = getMessage[2];
            }
            if (mode != null && id != null & message != null) {
                if (mode.equals("GM")) { // 일반 대화
                    String message_GM = this.id + " : " + message;
                    for (ServerAgent a : agents) {
                        a.os.write(message_GM.getBytes(StandardCharsets.UTF_8));
                        a.os.flush();
                    }
                } else if (mode.equals("SM")) { //귓속말
                    for (ServerAgent a : agents) {
                        if (id == a.id) {
                            String message_SM = this.id + " : " + message;
                            a.os.write(message_SM.getBytes(StandardCharsets.UTF_8));
                            a.os.flush();
                        }
                    }
                } else if (mode.equals("WD")) { //추방
                    Boolean access = false;
                    ServerAgent agentFocus = null;
                    for (ServerAgent a : agents) {
                        if (a.master) {
                            if (a.id == id) {
                                access = true;
                            }
                            if (message == a.id) {
                                agentFocus = a;
                            }
                        }
                    }
                    if (access && agentFocus != null) {
                        agents.remove(agentFocus);
                        WaAndWr(message);
                    } else if (!access) {
                        String sendMessage = "권한이 없습니다.";
                        os.write(sendMessage.getBytes(StandardCharsets.UTF_8));
                        os.flush();
                    } else if (agentFocus == null) {
                        String sendMessage = "존재하지 않는 사용자입니다.";
                        os.write(sendMessage.getBytes(StandardCharsets.UTF_8));
                        os.flush();
                    }
                }
            } else {
                String sendMessage = "메세지 전송 형식이 올바르지 않습니다..";
                os.write(sendMessage.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

        }
    }

    private void Dc() throws IOException {
        for (ServerAgent a : agents) {
            if (a.id == id) {
                continue;
            } else {
                String sendMessage = id + "가 접속을 종료했습니다.";
                a.os.write(sendMessage.getBytes(StandardCharsets.UTF_8));
                a.os.flush();
            }
        }

        agents.remove(this);
    }

    private void WaAndWr(String id) throws IOException {
        for (ServerAgent a : agents) {
            if (a.id.equals(id)) {
                String sendMessage = "방장으로부터 추방당하였습니다.";
                a.os.write(sendMessage.getBytes(StandardCharsets.UTF_8));
                os.flush();
            } else {
                String sendMessage = id + "가 추방당하였습니다.";
                a.os.write(sendMessage.getBytes(StandardCharsets.UTF_8));
                a.os.flush();
            }
        }
    }


    private void Ur() throws IOException {
        String list = "";
        for (ServerAgent a : agents) {
            list += a.id + " " + a.name;
        }
        byte[] bytes = list.getBytes();
        os.write(bytes);
        os.flush();

        agents.add(this);

        Jr();
    }

    private void Jr() throws IOException {
        for (ServerAgent a : agents) {
            if (this.id == a.id) {
                continue;
            } else {
                String newClient = this.id + " " + this.name + "님이 들어오셨습니다.";
                a.os.write(newClient.getBytes(StandardCharsets.UTF_8));
                a.os.flush();
            }
        }


        runningServer();
    }

    private void Id() throws Exception {
        bytes = new byte[BYTE_LENGTH];
        int length = is.read(bytes);
        String read = new String(bytes, 0, length);

        System.out.println(read);
        String[] getProfile = read.split(" ");


        //DR
        id = getProfile[0];
        name = getProfile[1];
        if(agents.size() > 0){
            for (ServerAgent a : agents) {
                System.out.println("어이");
                if (a.id == this.id) {
                    bytes = "중복된 아이디가 존재하여 종료합니다.".getBytes((StandardCharsets.UTF_8));
                    os.write(bytes);
                    os.flush();
                    close();
                    throw new Exception();
                }
            }
        }

        String sendMessage = "접속 성공...";
        os.write(sendMessage.getBytes(StandardCharsets.UTF_8));
        os.flush();

        if (agents.size() <= 0) {
            master = true;
            sendMessage = "지금부터 당신은 방장입니다.";
            os.write(sendMessage.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }
    }

    public void prepareTalking() throws Exception {
        is = socket.getInputStream();
        os = socket.getOutputStream();
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
