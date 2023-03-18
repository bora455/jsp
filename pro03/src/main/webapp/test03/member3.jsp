<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="id"  value="hong" scope="page" />
<c:set var="pwd"  value="1234" scope="page" />
<c:set var="name"  value="${'홍길동' }" scope="page" />
<c:set var="age"  value="${22}" scope="page" />
<c:set var="height"  value="${177}" scope="page" />

<!-- 변수 age와 height를 삭제함 -->
<c:remove var="age" />
<c:remove var="height" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" width="100%">
		<tr align="center" bgcolor="#99ccff">
			<td width"7%">아이디</td>
			<td width"7%">비밀번호</td>
			<td width"7%">이름</td>
			<td width"7%">나이</td>
			<td width"7%">키</td>
		</tr>
		<!-- 표현언어에서 변수 값 출력 -->
		<tr align="center">
			<td>${id}</td>
			<td>${pwd}</td>
			<td>${name}</td>
			<td>${age}</td>
			<td>${height}</td>
		</tr>
	</table>
</body>
</html>