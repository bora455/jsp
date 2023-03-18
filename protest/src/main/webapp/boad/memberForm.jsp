<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, protest.*"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 추가</title>
</head>
<body>
	<h1>학생 추가</h1>
	<hr>
	<form action="${contextPath }/boad/addstudent" method="post">
		<table>
			<tr>
				<td width="110"><p>이름</p></td>
				<td width="50">
				<input type="text" name="username" value="${memInfo.name}">
				</td>
			</tr>
			<tr>
				<td width="110"><p>대학</p></td>
				<td width="50">
				<input type="text" name="univ" value="${memInfo.univ}">
				</td>
			</tr>
			<tr>
				<td width="110"><p>생일</p></td>
				<td width="50">
				<input type="text" name="birth" value="${memInfo.birth}">
				</td>
			</tr>
			<tr>
				<td width="110"><p>이메일</p></td>
				<td width="50">
				<input type="text" name="email" value="${memInfo.email}">
				</td>
			</tr>
			<tr>
				<td width="100">
				<input type="submit" value="등록">
				<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>