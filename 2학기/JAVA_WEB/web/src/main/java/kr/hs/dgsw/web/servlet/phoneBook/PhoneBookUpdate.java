package kr.hs.dgsw.web.servlet.phoneBook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/phoneBook/update.do")
public class PhoneBookUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		RestResult result = new RestResult();

		try {
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setIdx(Integer.parseInt(request.getParameter("idx")));
			phoneNumber.setName(request.getParameter("name"));
			phoneNumber.setPhoneNumber(request.getParameter("phoneNumber"));

			PhoneBookService service = new PhoneBookServiceImpl();
			service.update(phoneNumber);

			result.setResultCode("Success");

		} catch (Exception e) {
			e.printStackTrace();

			result.setResultCode("Fail");
			result.setResultMessage(e.getMessage());
		}

		response.getWriter().append(new Gson().toJson(result));
	}
}