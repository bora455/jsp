<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	List dataList=new ArrayList();
	dataList.add("hello");
	dataList.add("world");
	dataList.add("안녕하세요");
%>
<c:set var="list" value="<%=dataList %>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 반복변수 i를 1부터 10까지 1씩 증가시키면서 반복문 수행 -->
	<c:forEach var="i" begin="1" end="10" step="1" varStatus="loop">
		i=${i} &nbsp;&nbsp;&nbsp; 반복횟수: ${loop.count } <br>
	</c:forEach>
	<br>
	
	<!-- 반복변수 i를 1부터 10까지 2씩 증가시키면서 반복문 수행 -->
	<c:forEach var="i" begin="1" end="10" step="2" >
		5 * ${i} = ${5*i} <br>
	</c:forEach>
	<br>
	
	<!-- ArrayList 같은 컬렉션 객체에 저장된 객체(데이터)를 반복해서 반복 변수 data에 하나씩 가져와서 처리함 -->
	<c:forEach var="data" items="${list}" >
		${data } <br>
	</c:forEach>
	<br>
	
	<!-- 구분자 ,(콤마)를 이용해 문자열 분리해서 출력 -->
	<c:set var="fruits" value="사과, 파인애플, 바나나, 망고, 귤" />
	<c:forTokens var="token" items="${fruits}" delims=",">
		${token} <br>
	</c:forTokens>
</body>
</html>