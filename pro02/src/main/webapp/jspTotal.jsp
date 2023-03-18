<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JSP 종합 예제</h2>
	<hr>
	<%! 
	String[] members = { "이미소", "이태경", "이사랑" };
	int num1 = 10;
	int calc(int num2){ return num1 + num2; }
	%>
	<!-- 배열과 함수를 선언 -->
	<h3>
	1. jsp 주석
	<!-- html 주석: 화면에서는 안 보이고 소스 보기에는 보임 -->
	<!--  -->
	</h3>
	<h3> 2. calc(10) 메서드 실행 결과 : <%=calc(10) %></h3>
	<!-- calc( ) 메서드를 호출 -->
	<hr>
	<h3> 3. include: hello.jsp </h3>
	<%@ include file="../hello.jsp" %>
	<hr>
	<h3> 4. 스크립트(배열 데이털 출력) </h3>
	<ul>
	<% for(String name:members) { %>
	<li><%=name %></li>
	<% } %>
	<!-- members 배열의 값을 모두 출력하기 -->
	</ul>
</body>
</html>