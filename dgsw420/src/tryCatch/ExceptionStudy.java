package tryCatch;

public class ExceptionStudy { 
	public static void methodA() throws Exception {
		Exception e = new Exception("직접 만든 예외");
		//exception은 예외 처리를 꼭 해줘야 함
		throw e;
	}
	
	public static void methodB() {
		RuntimeException e = new RuntimeException("직접 만든 예외");
		//RuntimeException은 예외 처리를 하지 않아도 됨
		
		throw e;
	}

	public static void methodC() throws Exception {
	}
	
	public static void methodD() throws RuntimeException {
	}
	
	public static void main(String[] args) {
		try {
			methodA();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		methodB();
		//methodC();  -> try / catch 사용 -> Exception으로 에러가 있다고 명시했기 때문에
		methodD();
	}
}
