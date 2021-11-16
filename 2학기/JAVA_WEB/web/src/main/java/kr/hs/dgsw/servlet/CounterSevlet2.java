package kr.hs.dgsw.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.hs.dgsw.repository.CounterRepository;
import kr.hs.dgsw.repository.CounterRepositoryWithFile;

@WebServlet("/counter2")
public class CounterSevlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CounterRepository repository = new CounterRepositoryWithFile();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		repository.addCount();
		int count = repository.getCount();
		
		response.setCharacterEncoding("EUC-KR");
		response.getWriter().append("방문자 : ").append(count + "명");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
