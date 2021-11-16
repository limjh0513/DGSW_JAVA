package kr.hs.dgsw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/play")
public class PlayServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = null;

		// 로그인 여부 검사
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cok : cookies) {

					if ("dgsw_cookie".equals(cok.getName())) {
						id = cok.getValue();
					}
				}
			}
			String message = null;
			if (id == null) {
				message = "권한이 없습니다.";
			} else {
				message = "당신의 아이디는 " + id + "입니다.";
			}

			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(message);
		} catch (Exception e) {

			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append("오류발생");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
