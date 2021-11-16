package tcpServer;

public enum ErrorCode {
	PORT_ALREADY_OCCUPIED("1001", "Port�� �̹� ������Դϴ�."),
	SERVER_SOCKET_FAIL("1002", "���� ���� ���� �߻�"),
	STREAM_CREATION_FAIL("1003", "Input/output stream ���� ����"),
	MESSAGE_SENDING_FAIL("1004", "�޽��� �۽� ����"),
	SERVER_SOCKET_CHANNEL_FAIL("1005", "���� ���� ä�� ���� �߻�"),
	SERVER_SOCKET_CLOSE_FAIL("2001", "���� ���� close ����"),
	SERVER_SOCKET_CHANNEL_CLOSE_FAIL("2002", "���� ���� close ����"),
	COMMON_ERROR("9999");
	
	
	private String code;
	
	private String message;
	
	private ErrorCode() {
		this("9999");
	}
	
	private ErrorCode(String code) {
		this(code, null);
	}
	
	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
