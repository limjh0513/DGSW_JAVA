package kr.hs.dgsw.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieSeesionManager implements SessionManager {

	public static final String COOKIE_NAME = "cookie_name";

	@Override
	public boolean isAuthorized(HttpServletRequest request) {
		if (getId(request) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public String getId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(COOKIE_NAME)) {
					return c.getValue();
				}
			}
		}

		return null;
	}

	@Override
	public void doLogin(HttpServletRequest request, HttpServletResponse response, String id) {
		Cookie cookie = new Cookie(COOKIE_NAME, id);
		response.addCookie(cookie);
	}

	@Override
	public void doLogout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(COOKIE_NAME, "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

}
