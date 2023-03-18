<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*,sec01.ex01.*"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="membersList" class=java.util.ArrayList" />
<jsp:useBean id="membersMap" class=java.util.HashMap" />  
<%
	membersMap.put("id", "park2");
	membersMap.put("pwd", "4321");
	membersMap.put("name", "박지성");
	membersMap.put("email", "park2@test.com");
	MemberBean m1 = new MemberBean("son", "1234", "손흥민", "son@test.com");
	MemberBean m2 = new MemberBean("ki", "4321", "기성용", "ki@test.com");
	membersList.add(m1);
	membersList.add(m2);
	membersMap.put("membersList", membersList);
%>
<c:set var="membersList" value="${membersMap.memberList}" />
<!-- c:set태그를 이용해 HashMap에 저장된 ArrayList에 접근하기 위해 사용하기 편리한 이름으로 설정 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> 
	<tr align="center">
		<td>${membersMap.id}</td>
		<td>${membersMap.pwd}</td>
		<td>${membersMap.name}</td>
		<td>${membersMap.email}</td>
	</tr>
	<!-- c:set태그로 설정한 변수 이름으로 접근하여 출력 -->
	<tr align="center">
		<td>${memberList[0].id}</td>
		<td>${memberList[0].pwd}</td>
		<td>${memberList[0].name}</td>
		<td>${memberList[0].email}</td>
	</tr>
	<tr align="center">
		<td>${memberList[1].id}</td>
		<td>${memberList[1].pwd}</td>
		<td>${memberList[1].name}</td>
		<td>${memberList[1].email}</td>
	</tr>
</body>
</html>