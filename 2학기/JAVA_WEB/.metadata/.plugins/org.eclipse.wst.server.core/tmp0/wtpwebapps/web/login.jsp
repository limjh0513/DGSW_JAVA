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
			<lable>���̵�</lable>
			<input type="text" name="id" />
		</div>

		<div>
			<lable>��й�ȣ</lable>
			<input type="password" name="password" />
		</div>

		<button type="submit">�α���</button>
	</form>
</body>
</html>