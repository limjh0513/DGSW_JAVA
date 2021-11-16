package kr.hs.dgsw.javaClass.serverCode;

import kr.hs.dgsw.javaClass.server.SocketWorkerAdapter;

import java.io.IOException;

public class EchoServerWorker extends SocketWorkerAdapter {

	@Override
	public void startTalking() throws IOException {
		
		
	}

	@Override
	public void listen(String message) throws IOException {
		this.os.write(message.getBytes());
	}
	
}
