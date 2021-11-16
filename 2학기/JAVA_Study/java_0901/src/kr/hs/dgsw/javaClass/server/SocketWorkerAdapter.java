package kr.hs.dgsw.javaClass.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class SocketWorkerAdapter implements SocketWorker {

	private Socket socket;
	private InputStream is;
	private OutputStream os;

	private Thread listenerThread;

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

	private class Listener implements Runnable {

		@Override
		public void run() {

		}

	}

}
