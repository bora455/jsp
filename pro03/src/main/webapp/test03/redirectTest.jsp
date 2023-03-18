<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*,sec01.ex01.*"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<!-- 리다이렉트할 페이지 설정 -->
		<c:redirect url="/test01/member1/jsp">
			<!-- 리다이렉트할 페이지로 전달할 매개변수 설정 -->
			<c:param name="id" value="${'hong'}" />
			<c:param name="pwd" value="${'1234'}" />
			<c:param name="name" value="${'김보미'}" />
			<c:param name="email" value="${'hong@test.com'}" />
		</c:redirect>
</body>
</html>