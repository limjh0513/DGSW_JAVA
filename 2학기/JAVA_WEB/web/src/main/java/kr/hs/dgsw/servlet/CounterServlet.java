package kr.hs.dgsw.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/counter")
public class CounterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		read();
		count++;
		response.setCharacterEncoding("EUC-KR");
		response.getWriter().append("방문자 수 : " + count);
		write();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void write() {
		File dir = new File("C:\\study\\a");
		File file = new File(dir, "aaa.txt");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			System.out.println(count);
			fos.write(count);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read() {
		File dir = new File("C:\\study\\a");
		File file = new File(dir, "aaa.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			count = fis.read();
			System.out.println(count);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
