package kr.hs.dgsw.web.servlet.phoneBook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/phoneBook/delete.do")
public class PhoneBookDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		RestResult result = new RestResult();
		
		try {
			String idxList = request.getParameter("idxList");
			String[] idxArray = idxList.split(",");

			PhoneBookService service = new PhoneBookServiceImpl();
			
			for(String sIdx:idxArray) {
				int idx = Integer.parseInt(sIdx);
				service.delete(idx);
			}
			
			
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setName(request.getParameter("name"));
			phoneNumber.setPhoneNumber(request.getParameter("phoneNumber"));
			
			phoneNumber = service.create(phoneNumber);
			
			result.setResultCode("Success");
			result.setData(phoneNumber);
		} catch (Exception e) {
			result.setResultCode("Fail");
			result.setResultMessage(e.getMessage());
		}

		response.getWriter().append(new Gson().toJson(result));
	}

}
