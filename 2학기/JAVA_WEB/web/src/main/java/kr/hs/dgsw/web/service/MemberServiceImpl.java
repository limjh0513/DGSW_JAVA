package kr.hs.dgsw.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.hs.dgsw.web.domain.Member;

public class MemberServiceImpl implements MemberService {

	@Override
	public void registerMember(Member member) {
		try {
			// DB 연결
			Connection con = ConnectionManager.getConnection();
			// SQL 작성
			StringBuilder sql = new StringBuilder();
			sql.append("insert into member ");
			sql.append("(email, password, name, contact, birthday, ");
			sql.append("register_time) ");
			sql.append("VALUES ");
			sql.append("(?, ?, ?, ?, ?, ");
			sql.append("NOW()) ");

			// PreparedStatment 생성, 수행
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getContact());

			String sBirthday = member.getBirthday();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = dateFormat.parse(sBirthday);

			pstmt.setDate(5, new java.sql.Date(birthday.getTime()));

			pstmt.executeUpdate();
			// DB 연결 종료
			con.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("회원 가입 실패", e);
		}

	}

	@Override
	public boolean isEmailDuplicated(String email) {
		try {
			// DB 연결
			Connection con = ConnectionManager.getConnection();

			// SQL 작성
			StringBuilder sql = new StringBuilder();
			sql.append("select COUNT(*) ");
			sql.append(" FROM member ");
			sql.append(" WHERE email = ? ");

			// QUERY
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);

			// DB 종료
			con.close();
			rs.close();
			pstmt.close();

			return (count > 0);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public List<Member> getList() {
		try {
			Connection con = ConnectionManager.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("	email, ");
			sql.append("	name, ");
			sql.append("	contact, ");
			sql.append("	birthday, ");
			sql.append("	register_time ");
			sql.append(" FROM member ");
			sql.append("ORDER BY register_time ");

			PreparedStatement pstmt = con.prepareStatement(sql.toString());

			List<Member> memberList = new ArrayList<Member>();
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setContact(rs.getString("contact"));

				SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date birthday = rs.getDate("birthday");

				member.setBirthday(dataFormat.format(birthday));

				Date registerTime = rs.getTimestamp("register_time");
				member.setRegisterTime(dataFormat.format(registerTime));
				
				memberList.add(member);
			}
			pstmt.close();
			rs.close();
			con.close();
			
			return memberList;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		try {
			MemberService service = new MemberServiceImpl();
			Member member = new Member();
			member.setEmail("abcd@gmail.dfi");
			member.setPassword("1123435");
			member.setName("테스트");
			member.setContact("010-1102-4396");
			member.setBirthday("2035-04-04");
			// service.registerMember(member);
			System.out.println(service.isEmailDuplicated("abcd@gmail.com"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
