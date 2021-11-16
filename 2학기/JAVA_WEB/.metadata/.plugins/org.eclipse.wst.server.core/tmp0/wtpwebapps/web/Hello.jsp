<%@page import="kr.hs.dgsw.service.People"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String name = request.getParameter("myName");
String sYear = request.getParameter("birthYear");
int birthYear = Integer.parseInt(sYear);

People p = new People(name, birthYear);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	for (int i = 0; i < 10; i++) {
	%>
	<p>
		안녕하세요.
		<%=name%>
		님
	</p>
	<p><%=name%>님의 나이는
		<%=p.getAge()%>살 입니다.
	</p>

	<%
	}
	%>
</body>
</html>