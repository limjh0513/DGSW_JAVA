package kr.hs.dgsw.javaClass.server;

import java.io.IOException;
import java.net.Socket;

public class CommonClient {

	private Socket socket;

	public void connetct(String address, int port) throws IOException {
		this.socket = new Socket(address, port);
	}
	
	public void execute() throws IOException{
		SocketWorker socketWorker = new SocketWorkerAdapter() {
			
			@Override
			public void startTalking() throws IOException {
				
			}
		};
		socketWorker.setSocket(socket);
		socketWorker.prepareTalking();
		socketWorker.startTalking();
	}
	
	public static void main(String[] args) {
		try {
			CommonClient client = new CommonClient();
			client.connetct("127.0.0.1", 8000);
			client.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
