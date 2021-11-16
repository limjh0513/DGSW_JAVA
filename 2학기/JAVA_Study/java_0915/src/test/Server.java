package test;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private File folder = new File("C:\\");

    private static final int PORT = 8080;

    public void startServer() throws Exception{
        serverSocket = new ServerSocket(8080);

        while(true){
            Socket socket = serverSocket.accept();
            startWork(socket);
        }
    }

    private void startWork(Socket socket) {
        try{
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            String files = "";

            byte[] bytes = new byte[10000];

            while (true) {
                int length = is.read(bytes);

                String message = new String(bytes, 0, length);

                if(message.equals("ls")){
                    for (File file : folder.listFiles()) {
                        System.out.println(file.getName());
                        files = files + " " + file;
                    }
                }

                os.write(files.getBytes());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
