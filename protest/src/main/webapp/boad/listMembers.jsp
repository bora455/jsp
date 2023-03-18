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
<title>학생 목록</title>
</head>
<body>
<h1>학생 정보</h1>
<a href="${contextPath}/boad/addstudent"><p>[새로고침]</p></a>

	<table align="center" width="100%" border="1">
		<tr align="center">
			<td width="7%">ID</td>
			<td width="7%">NAME</td>
			<td width="7%">UNIV</td>
			<td width="7%">BIRTH</td>
			<td width="7%">EMAIL</td>
		</tr>
		
		<c:choose>
		<c:when test="${!empty list}">
			<c:forEach var="list" items="${list}">
				<tr>
				<td width="7%">${list.id }</td>
				<td width="7%">${list.name }</td>
				<td width="7%">${list.univ }</td>
				<td width="7%">${list.birth }</td>
				<td width="7%">${list.email }</td>
				</tr>
			</c:forEach>
		</c:when>
		</c:choose>
	</table>
</body>
</html>