package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;

    private InputStream is;
    private OutputStream os;

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int PORT = 8080;

    private Scanner sc;

    public void connect() throws IOException {
        socket = new Socket(SERVER_ADDRESS, PORT);
        System.out.println(socket.isConnected());
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

    }

    public String receiveMessage() throws Exception {
        byte[] buffer = new byte[10000];

        int length = is.read(buffer);
        return new String(buffer, 0, length);
    }

    public void processUserInput() throws Exception {
        sc = new Scanner(System.in);
        String message = sc.nextLine();
        sendMessage(message);
        String returnMessage = receiveMessage();
        System.out.println(returnMessage);
        sc.close();
        disconnect();
    }

    private void sendMessage(String message) throws Exception {
        byte[] bytes = message.getBytes();
        os.write(bytes);
    }

    public void prepareTalking() throws Exception {
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }


    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.connect();
            client.prepareTalking();
            client.processUserInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
