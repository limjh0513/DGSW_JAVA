package kr.hs.dgsw.javaClass.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendClient {
	private Socket socket;

	private InputStream is;
	private OutputStream os;

	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static final int PORT = 8000;

	private Scanner sc;

	public void connect() throws IOException {
		socket = new Socket(SERVER_ADDRESS, PORT);
		System.out.println(socket.isConnected());

		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

	public String  receiveMessage() throws Exception {
		byte[] buffer = new byte[1024];

		int length = is.read(buffer);
		return new String(buffer, 0, length);
	}

	public void processUserInput() throws Exception {
		sc = new Scanner(System.in);
		int value1;
		int value2;

		while (true) {
			value1 = sc.nextInt();
			value2 = sc.nextInt();
			if (value1 == 0 && value2 == 0) {
				break;
			}

			String message = String.format("%d,%d", value1, value2);
			sendMessage(message);
			String returnMessage = receiveMessage();
			System.out.println("받은 메시지 : " + returnMessage);
		}
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

	public void startTalking() throws IOException {
		String message = "HELLO";
		byte[] bytes = message.getBytes();
		os.write(bytes);
		os.flush();
	}

	public static void main(String[] args) {
		try {
			SendClient client = new SendClient();
			client.connect();
			client.prepareTalking();
			client.processUserInput();
//			client.startTalking();
//			client.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
