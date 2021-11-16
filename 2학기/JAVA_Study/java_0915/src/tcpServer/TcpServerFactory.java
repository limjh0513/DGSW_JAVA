package tcpServer;

import tcpServer.type.blocking.BlockingServer;
import tcpServer.type.nonblocking.NonBlockingServer;
import tcpServer.type.socket.SocketServer;

public class TcpServerFactory {

	public static final String SOCKET_SERVER = "_socket";
	public static final String NIO_BLOCKING_SERVER = "_nio_block";
	public static final String NIO_NONBLOCKING_SERVER = "_nio_nonblock";
	
	public static TcpServer make(String type) {
		TcpServer server = null;
		
		if (SOCKET_SERVER.equals(type)) {
			server = new SocketServer();
		} else if (NIO_BLOCKING_SERVER.equals(type)) {
			server = new BlockingServer();
		} else if (NIO_NONBLOCKING_SERVER.equals(type)) {
			server = new NonBlockingServer();
		}
		
		return server;
	}
	
}
