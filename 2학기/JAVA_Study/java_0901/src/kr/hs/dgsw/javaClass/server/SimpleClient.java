package kr.hs.dgsw.javaClass.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleClient {
	private Socket socket;

	private InputStream is;
	private OutputStream os;

	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static final int PORT = 8000;

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

	public void prepareTalking() throws Exception {
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}

	public void startTalking() throws IOException {
		String message = "HELLO";
		byte[] bytes = message.getBytes();
		os.write(bytes);
	}

	public static void main(String[] args) {
		try {
			SimpleClient client = new SimpleClient();
			client.connect();
			client.prepareTalking();
			client.startTalking();
			client.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
