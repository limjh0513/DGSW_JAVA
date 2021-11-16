package tcpServer.type.blocking;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tcpServer.ErrorCode;
import tcpServer.SessionsCallback;
import tcpServer.TcpServer;
import tcpServer.TcpServerException;

public class BlockingServer implements TcpServer {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	protected ServerSocketChannel serverSocketChannel;
	
	protected SessionsCallback sessionsCallback;

	@Override
	public void start(int port) throws TcpServerException {
		
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(true);
			serverSocketChannel.bind(new InetSocketAddress(port));
			logger.info("Server started");
			
			SocketChannel socketChannel = null;
			while (true) {
				socketChannel = serverSocketChannel.accept();
				makeAgent(socketChannel);
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
			serverSocketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new TcpServerException(ErrorCode.SERVER_SOCKET_CHANNEL_CLOSE_FAIL);
		}
	}


	@Override
	public void setSessionsCallback(SessionsCallback callback) {
		this.sessionsCallback = callback;
	}

	protected void makeAgent(SocketChannel socketChannel) {
		BlockingServerAgent agent = new BlockingServerAgent(socketChannel, sessionsCallback);
		agent.start();
	}

}
