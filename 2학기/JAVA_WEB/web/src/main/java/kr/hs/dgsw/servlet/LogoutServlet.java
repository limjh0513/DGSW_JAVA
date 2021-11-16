package kr.hs.dgsw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		//아이디 비밀번호 검증 완료
		Cookie cookie = new Cookie("dgsw_cookie", "");
		cookie.setMaxAge(0);
		//cookie.setMaxAge(1800);
		//cookie.setMaxAge(-1);
		response.addCookie(cookie);
	
		
		response.getWriter().append("OK");
		response.sendRedirect("http://localhost:8080/web/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
