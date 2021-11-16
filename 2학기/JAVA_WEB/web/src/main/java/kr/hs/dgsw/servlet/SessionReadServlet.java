package kr.hs.dgsw.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session_read")
public class SessionReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		
		//session.invalidate(); // 세션을 refresh해서 다시 만듬
		//session.removeAttribute("id"); //하나만 날릴 때

		Date now = (Date) session.getAttribute("now");
		String name = (String) session.getAttribute("myName");
		int num = (Integer) session.getAttribute("number");

		StringBuilder buffer = new StringBuilder();
		buffer.append("now : ").append(now).append("<br/>");
		buffer.append("name : ").append(name).append("<br/>");
		buffer.append("num : ").append(num).append("<br/>");

		response.getWriter().append(buffer.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
