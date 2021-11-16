package kr.hs.dgsw.service;

public class SessionManagerMaker {

	private static SessionManager manager = new SessionSessionManager();

	public static SessionManager make() {
		return manager;
	}
}
