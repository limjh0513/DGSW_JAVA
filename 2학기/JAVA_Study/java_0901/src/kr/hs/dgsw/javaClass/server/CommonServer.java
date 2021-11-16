package kr.hs.dgsw.javaClass.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CommonServer {

	private ServerSocket serverSocket;

	public void startServer(int port) {
		try {
			serverSocket = new ServerSocket(port);

			while (true) {
				Socket socket = serverSocket.accept();
				Agent agent = new Agent(socket);
				new Thread(agent).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class Agent implements Runnable {

		private Socket socket;

		public Agent(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				SocketWorker socketWorker = new EchoServerWorker();
				socketWorker.setSocket(socket);
				socketWorker.prepareTalking();
				socketWorker.startTalking();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		try {
			CommonServer server = new CommonServer();
			server.startServer(8000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
