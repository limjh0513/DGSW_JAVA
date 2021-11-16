package tcpServer.dev;

import org.slf4j.LoggerFactory;

import tcpServer.SessionsCallback;
import tcpServer.TcpSession;

public class SimpleSessionsCallback implements SessionsCallback {

	@Override
	public void onConnected(TcpSession session) {
		LoggerFactory.getLogger(getClass()).debug("Connected");
		
	}

	@Override
	public void onDisconnected(TcpSession session) {
		LoggerFactory.getLogger(getClass()).debug("Disconnected");
	}

	@Override
	public void onMessageReceived(TcpSession session, byte[] data, int offset, int length) {
		LoggerFactory.getLogger(getClass()).debug("Received : " + byteArrayToHex(data, offset, length));
		session.send(data, 0, length);
	}

	public static String byteArrayToHex(byte[] bytes, int offset, int length) {
		StringBuilder buffer = new StringBuilder(bytes.length * 2);
		
		for (int i = offset; i < length ; i++) {
			buffer.append(String.format("%02x", bytes[i]));
		}
			
		return buffer.toString();
	}
}
