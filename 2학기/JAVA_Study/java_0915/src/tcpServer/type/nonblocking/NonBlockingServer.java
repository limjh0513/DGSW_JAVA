package tcpServer.type.nonblocking;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tcpServer.ErrorCode;
import tcpServer.SessionsCallback;
import tcpServer.TcpServer;
import tcpServer.TcpServerException;
import tcpServer.TcpSession;

public class NonBlockingServer implements TcpServer {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	protected Selector selector;
	
	protected ServerSocketChannel serverSocketChannel;
	
	protected SessionsCallback sessionsCallback;
	
	protected boolean running = true;
	
	@Override
	public void start(int port) throws TcpServerException {
		
		try {
			selector = Selector.open();
			
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress(port));
			
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			
			logger.info("Server started");
			
			
			while (true) {
				int keyCount = selector.select();
				
				if (keyCount > 0) {
					Set<SelectionKey> keys =  selector.selectedKeys();
					Iterator<SelectionKey> iterator = keys.iterator();

					while (iterator.hasNext()) {
						SelectionKey key = iterator.next();
						
						if (key.isAcceptable()) {
							accept(key);
						} else if (key.isReadable()) {
							read(key);
						} else if (key.isWritable()) {
							write(key);
						}
						
						iterator.remove();
					}
				} else {
					if (!running) {
						throw new AsynchronousCloseException();
					}
				}
			}
			
		} catch (BindException e) {
			throw new TcpServerException(ErrorCode.PORT_ALREADY_OCCUPIED);
		} catch (AsynchronousCloseException e) {
			logger.info("Server terminated");
		} catch (IOException e) {
			throw new TcpServerException(ErrorCode.SERVER_SOCKET_FAIL);
		}
	}

	@Override
	public void stop() {
		try {
			running = false;
			
			serverSocketChannel.close();
			selector.wakeup();
		} catch (IOException e) {
			e.printStackTrace();
			throw new TcpServerException(ErrorCode.SERVER_SOCKET_CHANNEL_CLOSE_FAIL);
		}
	}


	@Override
	public void setSessionsCallback(SessionsCallback callback) {
		this.sessionsCallback = callback;
	}

	protected void accept(SelectionKey selectionKey) {
		try {
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
			SocketChannel socketChannel = serverSocketChannel.accept();
			
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);
			
			TcpSession session = new NonBlockingServerSession(socketChannel, selector);

			SelectionKey key = socketChannel.keyFor(selector);
			key.attach(session);
			
			sessionsCallback.onConnected(session);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void read(SelectionKey selectionKey) {
		try {
			SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
			
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			byte[] readBytes = new byte[1024];
			byte[] bytes = new byte[0];
			int length;

			while ((length = socketChannel.read(buffer)) > 0) {
				buffer.flip();
				buffer.get(readBytes, 0, length);
				bytes = concatByteArray(bytes, readBytes, length);
			}
			
			TcpSession session = (TcpSession)selectionKey.attachment();

			if (bytes.length > 0) {
				sessionsCallback.onMessageReceived(session, bytes, 0, bytes.length);
			} else {
				socketChannel.close();
				
				sessionsCallback.onDisconnected(session);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void write(SelectionKey key) {
		NonBlockingServerSession session = (NonBlockingServerSession)key.attachment();
		session.sendPhysically();
	}
	
	protected static byte[] concatByteArray(byte[] first, byte[] second, int length) {
		byte[] result = new byte[first.length + second.length];
		System.arraycopy(first, 0, result, 0, 0);
		System.arraycopy(second, 0, result, first.length, length);
		
		return result;
	}

	
}
