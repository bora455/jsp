<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*,sec01.ex01.*"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	List memberList = new ArrayList();
	MemberBean m1 = new MemberBean("son", "1234", "손흥민", "son@test.com");
	MemberBean m2 = new MemberBean("ki", "4321", "기성용", "ki@test.com");
	MemberBean m3 = new MemberBean("park", "1212", "박지성", "park@test.com");
	memberList.add(m1);
	memberList.add(m2);
	memberList.add(m3);
%>
<c:set var="membersList" value="<%= memberList %>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" border="1">
		<tr align="center" bgcolor="lightgreen">
			<td width"7%">아이디</td>
			<td width"7%">비밀번호</td>
			<td width"5%">이름</td>
			<td width"5%">이메일</td>
		</tr>
		
		<!-- 반복문을 수행하면서 memberList에 저장된 MemberBean 객체가 차례대로 member에 할당됨 -->
		<c:forEach var="member" items="${membersList}">
		<tr align="center">
			<!-- 속성 이름으로 회원정보를 차례대로 출력 -->
			<td>${member.id}</td>
			<td>${member.pwd}</td>
			<td>${member.name}</td>
			<td>${member.email}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>