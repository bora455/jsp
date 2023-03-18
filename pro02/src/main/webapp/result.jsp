<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<%
	String userID = request.getParameter("userID");
	if(userID.length()==0) {
		/*
		RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
		dispatch.forward(request, response);
		*/
	%>	
	<jsp:forward page="login.jsp" />
	<!-- ID를 입력하지 않으면 태그를 사용해 로그인창으로 포워딩 -->
	<%
 	}
	%>
	 <h1> 환영합니다 <%= userID %>님!! </h1>
</body>
</html>