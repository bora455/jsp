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
	/* 세명의 회원정보를 MemberBean에 저장한 후 다시 ArrayList에 저장함 */
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
		
		<!-- memberList에 저장된 회원 수 만큼 반복변수 i를 0부터 1씩 증가시키면서 forEach문을 실행 -->
		<c:forEach var="i" begin="0" end="2" step="1">
		<tr align="center">
			<!-- 반복변수 i를 ArrayList의 인덱스로 사용해 회원정보를 차례대로 출력 -->
			<td>${membersList[i].id}</td>
			<td>${membersList[i].pwd}</td>
			<td>${membersList[i].name}</td>
			<td>${membersList[i].email}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>