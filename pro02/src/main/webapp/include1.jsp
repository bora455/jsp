<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%
	request.setCharacterEncoding("utf-8");
 	String name = request.getParameter("name");
 	String imgName = request.getParameter("imgName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	안녕하세요. 쇼핑몰 중심 JSP 시작입니다~~~
	<br>
	<jsp:include page="duke_image.jsp" flush="true">
	<!-- duke_image.jsp를 동적으로 전달 -->
		<jsp:param value="듀크" name="name"/>
		<jsp:param value="./images/1.jpg" name="imgName"/>
		<!-- param 액션 태그를 이용해 duke_image.jsp로 이름과 파일 이름 전달 -->
	</jsp:include>
	<br>
	안녕하세요. 쇼핑몰 중심 JSP 끝입니다~~~
</body>
</html>