<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
String message = "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body
	onload=<%
	Cookie[] cookies = request.getCookies();
	String id = null;
	if (cookies != null) {
		for (Cookie cok : cookies) {

			if ("dgsw_cookie".equals(cok.getName())) {
				id = cok.getValue();
			}
		}
	}
	if (id == null) {
		response.sendRedirect("http://localhost:8080/web/login.jsp");
	} else {
		message = id;
	}
%>>
	<p><%=message%></p>
	<a href="http://localhost:8080/web/logout">로그아웃</a>
</body>
</html>