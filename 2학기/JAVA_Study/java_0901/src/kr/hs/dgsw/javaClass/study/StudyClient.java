package kr.hs.dgsw.javaClass.study;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class StudyClient {
	public final static String SERVER_ADDRESS = "127.0.0.1";
	public final static int PORT = 8000;

	private Socket socket;

	private InputStream is;
	private OutputStream os;

	public void connect() throws UnknownHostException, IOException {
		socket = new Socket(SERVER_ADDRESS, PORT);
		System.out.println(socket.isConnected());

		try {
			Thread.sleep(5000L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnet() throws IOException {
		if (is != null) {
			is.close();
		}
		if (os != null) {
			os.close();

		}
		if (socket != null) {
			socket.close();
		}
	}

	public void talkingBefore() throws IOException {
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}

	public void startTalking() throws IOException {
		String message = "hello";
		byte[] bytes = message.getBytes();
		os.write(bytes);
		
		System.out.println("전달 완료");
	}

	public static void main(String[] args) {
		StudyClient client = new StudyClient();

		try {
			client.connect();
			client.talkingBefore();
			client.startTalking();
			client.disconnet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
