package kr.hs.dgsw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.hs.dgsw.service.SessionManager;
import kr.hs.dgsw.service.SessionManagerMaker;

@WebServlet("/LogoutPageServlet")
public class LogoutPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		SessionManager sm = SessionManagerMaker.make();
		sm.doLogout(request, response);
		

		response.sendRedirect("http://localhost:8080/web/login.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
