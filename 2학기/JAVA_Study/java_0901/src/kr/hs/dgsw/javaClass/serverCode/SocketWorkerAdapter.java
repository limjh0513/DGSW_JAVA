package kr.hs.dgsw.javaClass.serverCode;

import kr.hs.dgsw.javaClass.server.SocketWorker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class SocketWorkerAdapter implements SocketWorker {

	protected Socket socket;
	
	protected InputStream is;
	
	protected OutputStream os;
	
	protected Thread listenerThread;
	
	@Override
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void prepareTalking() throws IOException {
		this.is = socket.getInputStream();
		this.os = socket.getOutputStream();

		Listener listener = new Listener();
		listenerThread = new Thread(listener);
		listenerThread.setDaemon(true);
		listenerThread.start();
	}

	@Override
	public abstract void startTalking() throws IOException;

	public abstract void listen(String message) throws IOException;
	
	@Override
	public void disconnect() throws IOException {
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
	
	private class Listener implements Runnable
	{
		@Override
		public void run() {
			byte[] buffer = new byte[1024];
			int length;
			
			try {
				while (true) {
					length = is.read(buffer);
					listen(new String(buffer, 0, length));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

}
