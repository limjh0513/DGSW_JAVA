package tcpServer;

public class TcpServerException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7582465793107052204L;

	private ErrorCode errorCode;
	
	public TcpServerException() {
		super();
	}
	
	public TcpServerException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public TcpServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TcpServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public TcpServerException(String message) {
		super(message);
	}

	public TcpServerException(Throwable cause) {
		super(cause);
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
