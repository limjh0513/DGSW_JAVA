package kr.hs.dgsw.javaClass.server;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	private ServerSocket serverSocket;

	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static final int PORT = 8000;
	
	public void startSever() throws Exception{
		serverSocket = new ServerSocket(PORT);
		System.out.println("서버 소켓이 생성되었습니다. 클라이언트 접속 대기");
		
		
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("클라이언트 접속 됨 : " + socket.getInetAddress().toString());
			
			startTalking(socket);
		}
	}
	
	public void startTalking(Socket socket) {
		try {
			InputStream is = socket.getInputStream();
			
			byte[] bytes = new byte[4096];
			int length = is.read(bytes);
			
			String message = new String(bytes, 0, length);
			System.out.println("클라이언트 메세지 : " + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			SimpleServer simpleServer = new SimpleServer();
			simpleServer.startSever();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
