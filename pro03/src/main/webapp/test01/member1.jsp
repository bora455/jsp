<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false" %>
<%
	request.setCharacterEncoding("utf-8");
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
	<table align="center" border="1">
		<tr align="center" bgcolor="#99ccff">
			<td width"7%">아이디</td>
			<td width"7%">비밀번호</td>
			<td width"7%">이름</td>
			<td width"7%">이메일</td>
		</tr>
		
		<tr align="center">
			<td><%=id %></td>
			<td><%=pwd%></td>
			<td><%=name%></td>
			<td><%=email%></td>
		</tr>
			
		<tr align="center">
			<td>${param.id}</td>
			<td>${param.pwd}</td>
			<td>${param.name}</td>
			<td>${param.email}</td>
		</tr>
		</table>
</body>
</html>