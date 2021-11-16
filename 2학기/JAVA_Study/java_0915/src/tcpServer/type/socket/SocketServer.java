package tcpServer.type.socket;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tcpServer.ErrorCode;
import tcpServer.SessionsCallback;
import tcpServer.TcpServer;
import tcpServer.TcpServerException;
import tcpServer.TcpServerFactory;
import tcpServer.dev.SimpleSessionsCallback;


public class SocketServer implements TcpServer {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	protected ServerSocket serverSocket;
	
	protected SessionsCallback sessionsCallback;
	
	@Override
	public void start(int port) throws TcpServerException {
		try {
			serverSocket = new ServerSocket(port);
			logger.info("Server started");
			
			while (true) {
				Socket socket = serverSocket.accept();
				makeAgent(socket);
			}
		} catch (BindException e) {
			throw new TcpServerException(ErrorCode.PORT_ALREADY_OCCUPIED);
		} catch (SocketException e) {
			logger.info("Server terminated");
		} catch (IOException e) {
			throw new TcpServerException(ErrorCode.SERVER_SOCKET_FAIL);
		}
	}

	@Override
	public void stop() {
		try {
			if (serverSocket != null && serverSocket.isBound()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			throw new TcpServerException(ErrorCode.SERVER_SOCKET_CLOSE_FAIL);
		}
	}
	
	@Override
	public void setSessionsCallback(SessionsCallback callback) {
		this.sessionsCallback = callback;
	}
	
	protected void makeAgent(Socket socket) {
		SocketServerAgent agent = new SocketServerAgent(socket, sessionsCallback);
		agent.start();
	}
	
	public static void main(String[] args) {
		try {
			TcpServer server = TcpServerFactory.make(TcpServerFactory.SOCKET_SERVER);
			
			/*
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(1000L);
						server.stop();
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			}).start();
			*/
			
			SessionsCallback sessionsCallback = new SimpleSessionsCallback();
			server.setSessionsCallback(sessionsCallback);
			
			server.start(3000);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
