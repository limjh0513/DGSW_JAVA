package kr.hs.dgsw.javaClass.serverCode;

import kr.hs.dgsw.javaClass.server.SocketWorkerAdapter;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CommonClient extends SocketWorkerAdapter {

	public void connect(String address, int port) 
			throws IOException {
		this.socket = new Socket(address, port);
	}
	
	@Override
	public void startTalking() throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			String line = scanner.nextLine();
			
			if ("quit".equals(line)) {
				break;
			}
			
			this.os.write(line.getBytes());
		}
		
		scanner.close();
		disconnect();
	}

	@Override
	public void listen(String message) throws IOException {
		System.out.println(message);
	}
	
	public static void main(String[] args) {
		try {
			CommonClient client = new CommonClient();
			client.connect("127.0.0.1", 8000);
			client.prepareTalking();
			client.startTalking();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
	
}
