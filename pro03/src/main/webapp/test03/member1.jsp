<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*,sec01.ex01.*"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- core 태그 라이브러리를 사용하기 위해 반드시 선언해야함 -->
<%
	request.setCharacterEncoding("utf-8");
%>

<c:set var="id"  value="hong" scope="page" />
<c:set var="pwd"  value="1234" scope="page" />
<c:set var="name"  value="${'홍길동' }" scope="page" />
<c:set var="age"  value="${22}" scope="page" />
<c:set var="height"  value="${177}" scope="page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
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
		<tr align="center">
		<!-- 표현언어로 변수에 바로 접근하여 값을 출력 -->
			<td>${id}</td>
			<td>${pwd}</td>
			<td>${name}</td>
			<td>${age}</td>
			<td>${height}</td>
		</tr>
		</table>
</body>
</html>