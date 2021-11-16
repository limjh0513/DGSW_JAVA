package tcpServer.type.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import tcpServer.ErrorCode;
import tcpServer.SessionsCallback;
import tcpServer.TcpServerException;
import tcpServer.TcpSession;

public class SocketServerAgent implements TcpSession {
	
	public static final int BUFFER_SIZE = 1024; 

	//private Logger logger = LoggerFactory.getLogger(getClass());

	protected SessionsCallback sessionsCallback;
	
	protected Socket socket;
	
	protected InputStream is;
	
	protected OutputStream os;
	
	protected Thread listenerThread;
	
	
	public SocketServerAgent(Socket socket, SessionsCallback sessionsCallback) {
		this.sessionsCallback = sessionsCallback;
		this.socket = socket;
	}
	
	public void start() {
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
			listenerThread = new Thread(new Listener());
			listenerThread.setDaemon(true);
			listenerThread.start();
			
			if (sessionsCallback != null) {
				sessionsCallback.onConnected(this);
			}
		} catch (IOException e) {
			throw new TcpServerException(ErrorCode.STREAM_CREATION_FAIL);
		}
	}
	
	protected void close() {
		try {
			if (is != null) {
				is.close();
			}
			
			if (os != null) {
				os.close();
			}
			
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
		} finally {
			if (sessionsCallback != null) {
				sessionsCallback.onDisconnected(this);
			}
		}
		
	}
	
	@Override
	public void send(byte[] bytes) {
		send(bytes, 0, bytes.length);
	}
	
	@Override
	public void send(byte[] bytes, int offset, int length) {
		try {
			os.write(bytes, offset, length);
		} catch (IOException e) {
			throw new TcpServerException(ErrorCode.MESSAGE_SENDING_FAIL);
		}
	}
	
	@Override
	public void disconnect() {
		close();
		
		try {
			listenerThread.join();
			
		} catch (InterruptedException e) {
		}
	}
	
	private void onReceive(byte[] bytes, int length) {
		if (sessionsCallback != null) {
			sessionsCallback.onMessageReceived(this, bytes, 0, length);
		}
	}
	
	protected class Listener implements Runnable {
		@Override
		public void run() {
			byte[] bytes = new byte[BUFFER_SIZE];
			int length;
			
			try {
				while ((length = is.read(bytes)) >= 0) {
					onReceive(bytes, length);
				}
				
				close();
			} catch (IOException e) {
				
			}
		}
	}
	
}
