package kr.hs.dgsw.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String num = request.getParameter("num");
		if (!num.equals("")) {
			int n = Integer.parseInt(num);
			response.setCharacterEncoding("EUC-KR");
			response.getWriter().append("<h1>구구단</h1>").append("<h2>" + num + "단 </h2><br/>");
			for (int i = 1; i <= 9; i++) {
				int result = n * i;
				response.getWriter().append(num + " * " + i + " = " + result + "<br/>");
			}
		} else {
			response.setCharacterEncoding("EUC-KR");
			response.getWriter().append("잘못된 값을 입력했습니다.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
