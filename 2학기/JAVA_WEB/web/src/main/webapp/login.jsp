<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="http://localhost:8080/web/LoginPageServlet" method="get">
		<div>
			<lable>아이디</lable>
			<input type="text" name="id" />
		</div>

		<div>
			<lable>비밀번호</lable>
			<input type="password" name="password" />
		</div>

		<button type="submit">로그인</button>
	</form>
</body>
</html>