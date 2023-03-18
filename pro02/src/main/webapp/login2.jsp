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
	<% /* 브라우저에서 접속시에는 msg 갑을 가져와서 표시, 최초 접속시에는 null이르모 아무것도 표시 하지 않음 */
		String msg = request.getParameter("msg");
		if(msg != null) {
	%>
		<h1> <%=msg %></h1>
	<%
		}
	%>
	<form action="result2.jsp" method="post">
		아이디: <input type="text" name="userID"><br>
	 	비밀번호: <input type="password" name="userPw"><br>
	 	<input type="submit" value="로그인"> 
	 	<input type="reset" value="다시입력">
	</form>
</body>
</html>