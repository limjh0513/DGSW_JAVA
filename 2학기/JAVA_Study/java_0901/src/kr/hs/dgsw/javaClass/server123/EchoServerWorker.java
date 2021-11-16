package kr.hs.dgsw.javaClass.server123;

import java.io.IOException;

public class EchoServerWorker extends SocketWorkerAdapter {
	
	@Override
	public void startTalking() throws IOException {
		
	}

	@Override
	public void listen(String message) throws IOException {
		System.out.println(message);
		this.os.write(message.getBytes());
	}
}
