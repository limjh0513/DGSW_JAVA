<%@page import="kr.hs.dgsw.web.service.MemberServiceImpl"%>
<%@page import="org.apache.catalina.tribes.membership.MemberImpl"%>
<%@page import="kr.hs.dgsw.web.service.MemberService"%>
<%@page import="java.util.List"%>
<%@page import="kr.hs.dgsw.web.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
List<Member> memberList = null;

try {
	MemberService service = new MemberServiceImpl();

	memberList = service.getList();
} catch (Exception e) {
	e.printStackTrace();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�� ���</title>
<style>
h1 {
	width: 600px;
	margin: 10px auto 10px auto;
}

table {
	width: 600px;
	margin: 15px auto 10px auto;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ccc;
	padding: 3px;
}
</style>
</head>
<body>
	<h1>ȸ�� ���</h1>
	<table>
		<thead>
			<tr>
				<th>���̵�</th>
				<th>�̸�</th>
				<th>����ó</th>
				<th>����</th>
			</tr>
		</thead>
		<tbody>
			<%
			if (memberList == null || memberList.size() == 0) {
			%>

			<tr>
				<td colspan="4">���� �� ȸ���� ���ų�, �о� �� �� �����ϴ�.</td>
			</tr>
			<%
			} else {
			for (Member member : memberList) {
			%>
			<tr>
				<td><%=member.getEmail()%></td>
				<td><%=member.getName()%></td>
				<td><%=member.getContact()%></td>
				<td><%=member.getAge()%></td>

				<%
				}
				}
				%>
			
		</tbody>
	</table>

</body>
</html>