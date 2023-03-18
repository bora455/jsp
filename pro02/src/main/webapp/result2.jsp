<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%! /* login.jsp로 전달한 오류 메시지 선언 */
	String msg = "아이디를 입력하지 않았습니다. 아이디를 입력해 주세요.";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% /* 로그인 시 입력한 이름 가져옴 */
		String userID = request.getParameter("userID");
		/* 이름을 입력하지 않을 경우 태그를 이용해 오류메시지 전달 */
		if(userID.length()==0) {
	%>
		<jsp:forward page="login2.jsp">
		<jsp:param name="msg" value="<%= msg %>" />
		</jsp:forward>
	<%
		}
	%>
		<h1>환영합니다. <%=userID %>님!!! </h1>
</body>
</html>