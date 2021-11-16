package kr.hs.dgsw.web.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	public static Connection getConnection() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mariadb://211.53.209.159:3306/dgsw2021", "dgsw", "1111");
	}
}
