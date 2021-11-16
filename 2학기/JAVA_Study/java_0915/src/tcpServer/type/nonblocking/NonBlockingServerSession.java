package tcpServer.type.nonblocking;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import tcpServer.TcpSession;

public class NonBlockingServerSession implements TcpSession {

	private SocketChannel socketChannel;
	
	private Selector selector;
	
	private ByteBuffer buffer;
	
	public NonBlockingServerSession(SocketChannel socketChannel, Selector selector) {
		this.socketChannel = socketChannel;
		this.selector = selector;
	}
	
	@Override
	public void send(byte[] bytes, int offset, int length) {
		buffer = ByteBuffer.wrap(bytes, offset, length);
		
		SelectionKey selectionKey = socketChannel.keyFor(selector);
		selectionKey.interestOps(SelectionKey.OP_WRITE);
		selector.wakeup();
	}
	
	@Override
	public void send(byte[] bytes) {
		send(bytes, 0, bytes.length);
	}
	
	@Override
	public void disconnect() {
		// TODO
	}

	public void sendPhysically() {
		try {
			if (buffer != null) {
				socketChannel.write(buffer);
			}

			SelectionKey selectionKey = socketChannel.keyFor(selector);
			selectionKey.interestOps(SelectionKey.OP_READ);
			selector.wakeup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
