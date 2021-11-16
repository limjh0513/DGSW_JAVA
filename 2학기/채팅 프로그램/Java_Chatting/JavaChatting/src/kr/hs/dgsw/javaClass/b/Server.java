package kr.hs.dgsw.javaClass.b;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int PORT = 1200;
    public static ArrayList<Agent> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("서버 START");

            while (true) {
                System.out.println("접속 대기중");
                Socket socket = serverSocket.accept();
                Agent agent = new Agent(socket);
                clients.add(agent);
            }
        } catch (Exception e) {
        }
    }
}
