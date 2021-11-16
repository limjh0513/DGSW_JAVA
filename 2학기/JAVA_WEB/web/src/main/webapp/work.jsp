<%@page import="kr.hs.dgsw.service.SessionManagerMaker"%>
<%@page import="kr.hs.dgsw.service.CookieSeesionManager"%>
<%@page import="kr.hs.dgsw.service.SessionManager"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
SessionManager sm = SessionManagerMaker.make();

if(!sm.isAuthorized(request)){
	response.sendRedirect("http://localhost:8080/web/login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<p><%=sm.getId(request)%></p>
	<a href="http://localhost:8080/web/LogoutPageServlet">로그아웃</a>
</body>
</html>