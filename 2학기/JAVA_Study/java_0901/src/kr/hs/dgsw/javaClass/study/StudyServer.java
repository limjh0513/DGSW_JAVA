package kr.hs.dgsw.javaClass.study;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StudyServer {
	public final static String SERVER_ADDRESS = "127.0.0.1";
	public final static int PORT = 8000;

	public void startServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("서버 온");
		while (true) {
			Socket socket = serverSocket.accept();
			startTalking(socket);

			System.out.println("연결 완료");
		}
	}

	public void startTalking(Socket socket) {
		try {
			InputStream is = socket.getInputStream();
			byte[] bytes = new byte[4082];
			int length = is.read();

			String message = new String(bytes, 0, length);
			System.out.println(message);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		try {
			StudyServer server = new StudyServer();
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
