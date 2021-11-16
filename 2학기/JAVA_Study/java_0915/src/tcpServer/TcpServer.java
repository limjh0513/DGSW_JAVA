package tcpServer;

public interface TcpServer {

	public void start(int port) throws TcpServerException;
	
	public void stop();
	
	public void setSessionsCallback(SessionsCallback callback);
	
}
