package tcpServer;

public interface SessionsCallback {
	public void onConnected(TcpSession session);
	
	public void onDisconnected(TcpSession session);
	
	public void onMessageReceived(TcpSession session, byte[] data, 
			int offset, int length);
}
