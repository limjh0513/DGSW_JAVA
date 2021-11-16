package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcStudy {
	public static void main(String[] args) {
		try {
			//java.sql을 사용해서 jdbc 사용
	
			//데이터 베이스 접속
			Class.forName("org.mariadb.jdbc.Driver"); //jbm한테 이 클래스를 이용한다고 알려줌 -> 잘못된 값을 입력, 설치 문제 시 오류 발생!
			Connection con = DriverManager.getConnection(
					"jdbc:mariadb://211.53.209.159/dgsw_java", //주소
					"dgsw_student", //데이터베이스
					"1234");//비밀번호
			
			//데이터 베이스 읽어오기
			String sql = "SELECT * FROM phone_book";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "박지나"); //첫번째 물음표 (1부터 시작)
			ResultSet rs = pstmt.executeQuery();
			
			//데이터 베이스 출력
			while (rs.next()) {
				String name = rs.getString("name");
				String phone_number = rs.getString("phone_number");
				String address = rs.getString("address");
				int id = rs.getInt("id");
				System.out.println(id + " : " + name + " " + phone_number + " " + address);
			}
			rs.close();
			pstmt.close();
			
			//데이터 베이스 접속 종료
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
