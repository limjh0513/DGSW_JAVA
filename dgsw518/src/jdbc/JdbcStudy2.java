package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JdbcStudy2 {
	public void register (String name, String phoneNumber, String address) {
		try {
			//java.sql을 사용해서 jdbc 사용
	
			//데이터 베이스 접속
			Class.forName("org.mariadb.jdbc.Driver"); //jbm한테 이 클래스를 이용한다고 알려줌 -> 잘못된 값을 입력, 설치 문제 시 오류 발생!
			Connection con = DriverManager.getConnection(
					"jdbc:mariadb://211.53.209.159/dgsw_java", //주소
					"dgsw_student", //데이터베이스
					"1234");//비밀번호
			
			
			//데이터베이스 삽입
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO phone_book ");
			sql.append(" (name, phone_number, address) ");
			sql.append("VALUES ");
			sql.append(" (?, ?, ?) ");
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNumber);
			pstmt.setString(3, address);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			//데이터 베이스 접속 종료
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		JdbcStudy2 jdbcStudy = new JdbcStudy2();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름 입력 : ");
		String name = sc.nextLine();
		System.out.print("휴대폰 번호 입력 : ");
		String phoneNumber = sc.nextLine();
		System.out.print("주소 입력 : ");
		String address = sc.nextLine();
		
		jdbcStudy.register(name, phoneNumber, address);
		
		System.out.println("Insert 완료!!!");
		sc.close();
	}
}
