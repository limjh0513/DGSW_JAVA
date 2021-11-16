package kr.hs.dgsw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session_play")
public class SessionPlayServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		if (request.isRequestedSessionIdValid()) {
			HttpSession session = request.getSession();

			String id = (String) session.getAttribute("id");
			String pw = (String) session.getAttribute("password");

			StringBuilder buffer = new StringBuilder();

			buffer.append("id : ").append(id).append("<br/>");
			buffer.append("pw : ").append(pw).append("<br/>");

			response.getWriter().append(buffer);
		} else {
			response.getWriter().append("세션이 존재하지 않습니다.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
