package tcpServer;

public interface TcpSession {

	public void send(byte[] bytes);

	public void send(byte[] bytes, int offset, int length);
	
	public void disconnect();
}
