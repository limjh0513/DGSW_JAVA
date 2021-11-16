package kr.hs.dgsw.javaClass.server;

import java.io.IOException;
import java.net.Socket;

public interface SocketWorker {
	public void setSocket(Socket socket);
	
	public void prepareTalking() throws IOException;
	
	public void startTalking() throws IOException;
	
	public void disconnect() throws IOException;
}
