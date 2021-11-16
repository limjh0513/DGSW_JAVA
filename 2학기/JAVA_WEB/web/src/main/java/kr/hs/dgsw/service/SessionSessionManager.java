package kr.hs.dgsw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionSessionManager implements SessionManager{
	
	public static final String SESSION_NAME = "asndfip";

	@Override
	public boolean isAuthorized(HttpServletRequest request) {
		return getId(request)== null ? false : true;
	}

	@Override
	public String getId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (String) session.getAttribute(SESSION_NAME);
	}

	@Override
	public void doLogin(HttpServletRequest request, HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_NAME, id);
	}

	@Override
	public void doLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(SESSION_NAME);
	}

}
