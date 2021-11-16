package kr.hs.dgsw.web.servlet.member;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.hs.dgsw.web.domain.Member;
import kr.hs.dgsw.web.service.MemberService;
import kr.hs.dgsw.web.service.MemberServiceImpl;

@WebServlet("/member/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("EUC-KR");

		try {
			MemberService service = new MemberServiceImpl();
			
			//아이디 중복 여부 검사
			if(service.isEmailDuplicated(request.getParameter("email"))) {
				String url = "fail.jsp?reason=" + URLEncoder.encode("이미 사용 중인 아이디입니다.", "UTF-8");
				response.sendRedirect(url);
				System.out.println("중복!!!!");
				return;
			}
			
			
			//DB에 유저 등록
			Member member = new Member();
			member.setEmail(request.getParameter("email"));
			member.setPassword(request.getParameter("password"));
			member.setName(request.getParameter("name"));
			member.setContact(request.getParameter("contact"));
			member.setBirthday(request.getParameter("birthday"));
			service.registerMember(member);

			response.sendRedirect("register_success.html");
		} catch (Exception e) {
			e.printStackTrace();
			String url = "fail.jsp?reason="+e.getMessage();
			response.sendRedirect(URLEncoder.encode(url, "UTF-8"));
		}
		// doGet(request, response);
	}

}
