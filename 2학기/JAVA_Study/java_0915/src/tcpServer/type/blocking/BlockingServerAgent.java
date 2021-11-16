package tcpServer.type.blocking;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import tcpServer.ErrorCode;
import tcpServer.SessionsCallback;
import tcpServer.TcpServerException;
import tcpServer.TcpSession;

public class BlockingServerAgent implements TcpSession {
	public static final int BUFFER_SIZE = 1024; 
	
	//private Logger logger = LoggerFactory.getLogger(getClass());

	protected SessionsCallback sessionsCallback;
	
	protected SocketChannel channel;
	
	protected ByteBuffer byteBuffer;
	
	protected Thread listenerThread;
	
	
	public BlockingServerAgent(SocketChannel channel, SessionsCallback sessionsCallback) {
		this.sessionsCallback = sessionsCallback;
		this.channel = channel;
	}
	
	public void start() {
		this.byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
		
		listenerThread = new Thread(new Listener());
		listenerThread.setDaemon(true);
		listenerThread.start();
		
		if (sessionsCallback != null) {
			sessionsCallback.onConnected(this);
		}
	}
	
	protected void close() {
		try {
			if (channel != null) {
				channel.close();
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
			ByteBuffer buffer = ByteBuffer.allocate(length - offset);
			
			buffer.put(bytes, offset, length);
			buffer.flip();
			
			channel.write(buffer);
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
				while ((length = channel.read(byteBuffer)) >= 0) {
					byteBuffer.flip();
					byteBuffer.get(bytes, 0, length);
					
					onReceive(bytes, length);
				}
				
				close();
			} catch (IOException e) {
				
			}
		}
	}
	
}
